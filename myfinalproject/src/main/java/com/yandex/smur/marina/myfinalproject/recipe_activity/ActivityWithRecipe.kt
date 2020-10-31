package com.yandex.smur.marina.myfinalproject.recipe_activity

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.activity_web_page.ActivityWebPageWithRecipe
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import com.yandex.smur.marina.myfinalproject.sqlite_database.DBHelper
import com.yandex.smur.marina.myfinalproject.sqlite_database.DBHelperSelectedIngredients
import kotlinx.android.synthetic.main.activity_with_recipe.*
import org.json.JSONArray
import java.text.DecimalFormat

class ActivityWithRecipe : AppCompatActivity() {

    private lateinit var dataBaseSelectedRecipes: SQLiteDatabase
    private lateinit var databaseSelectedIngredients: SQLiteDatabase
    private lateinit var contentValues: ContentValues
    private lateinit var dbHelper: DBHelper
    private lateinit var dbHelperSelectedIngredients: DBHelperSelectedIngredients
    private lateinit var listSelectedRecipes: MutableList<RecipeDataModel>

    private lateinit var recipe: RecipeDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_recipe)

        dbHelper = DBHelper(this)
        dataBaseSelectedRecipes = dbHelper.writableDatabase

        dbHelperSelectedIngredients = DBHelperSelectedIngredients(this)
        databaseSelectedIngredients = dbHelperSelectedIngredients.writableDatabase

        contentValues = ContentValues()

        settingActivity()

        recipe = intent.getSerializableExtra("recipe") as RecipeDataModel

        initActivity(recipe)

        val list = recipe.listOfIngredients
        initIngredients(list)

        listSelectedRecipes = getSelectedRecipesFromDB()

        seeTheFullRecipe(recipe.urlRecipe, recipe.title)

        addToFavourites(recipe)

        back()

    }

    private fun settingActivity() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "    Recipe"
    }

    private fun seeTheFullRecipe(url: String, title: String) {
        buttonSeeTheFullRecipe.setOnClickListener {
            val intent = Intent(this@ActivityWithRecipe, ActivityWebPageWithRecipe::class.java)
            intent.putExtra("seeTheFullRecipe", url)
            intent.putExtra("seeTheFullRecipeTitle", title)
            startActivity(intent)
        }
    }

    private fun addToFavourites(recipe: RecipeDataModel) {
        buttonAddToFavourites.setOnClickListener {
            if (listSelectedRecipes!!.contains(recipe)) {
                Toast.makeText(this, "this recipe has already been added to favorites", Toast.LENGTH_LONG).show()
            } else
                addRecipeToDataBase(recipe)
        }
    }

    private fun back() {
        buttonBackFromActivityWithRecipe.setOnClickListener {
            finish()
        }
    }

    private fun initIngredients(listOfIngredients: MutableList<Ingredient>) {
        listWithIngredients.apply {
            adapter = IngredientsAdapter(listOfIngredients, object : IngredientsAdapter.OnclickListenerAdapter {
                override fun onItemClick(ingredient: Ingredient) {
                    addIngredientToShoppingList(ingredient)
                    Toast.makeText(this@ActivityWithRecipe, "Item was added to Shopping List", Toast.LENGTH_LONG).show()
                }
            })
        }
        listWithIngredients.layoutManager = GridLayoutManager(this@ActivityWithRecipe, 2)
    }

    private fun initActivity(recipe: RecipeDataModel) {
        Glide.with(this)
                .load(recipe.urlImage)
                .into(imageViewOnActivityWithRecipe)

        titleOnActivityWithRecipe.text = recipe.title

        textViewNumberOfServingOnActivityWithRecipe.text = recipe.numberOfServings.toString()

        val energyServing = recipe.energy / recipe.numberOfServings
        val energyServingFormat = DecimalFormat("#0.00").format(energyServing)

        val proteinServing = recipe.protein / recipe.numberOfServings
        val proteinServingFormat = DecimalFormat("#0.00").format(proteinServing)

        val fatServing = recipe.fat / recipe.numberOfServings
        val fatServingFormat = DecimalFormat("#0.00").format(fatServing)

        val carbsServing = recipe.carbs / recipe.numberOfServings
        val carbsServingFormat = DecimalFormat("#0.00").format(carbsServing)

        textViewEnergyOnActivityWithRecipe.text = energyServingFormat

        textViewProteinOnActivityWithRecipe.text = proteinServingFormat

        textViewFatOnActivityWithRecipe.text = fatServingFormat

        textViewCarbsOnActivityWithRecipe.text = carbsServingFormat
    }

    fun addRecipeToDataBase(recipe: RecipeDataModel) {
        contentValues.put("id", recipe.id)
        contentValues.put("urlImage", recipe.urlImage)
        contentValues.put("title", recipe.title)
        contentValues.put("numberOfServings", recipe.numberOfServings)
        contentValues.put("energy", recipe.energy)
        contentValues.put("protein", recipe.protein)
        contentValues.put("fat", recipe.fat)
        contentValues.put("carbs", recipe.carbs)
        contentValues.put("listOfIngredients", listToJson(recipe))
        contentValues.put("urlRecipe", recipe.urlRecipe)

        dataBaseSelectedRecipes.insert("selected_recipes", null, contentValues)
    }


    private fun listToJson(recipe: RecipeDataModel): String {
        val list = recipe.listOfIngredients
        val inputArray = list.toTypedArray()
        val gson = Gson()
        val inputString = gson.toJson(inputArray)
        return inputString
    }

    fun addIngredientToShoppingList(ingredient: Ingredient) {
        contentValues.put("id", ingredient.id)
        contentValues.put("ingredient", ingredient.ingredient)

        databaseSelectedIngredients.insert("selected_ingredients", null, contentValues)
    }

    private fun getSelectedRecipesFromDB(): MutableList<RecipeDataModel> {
        val list: MutableList<RecipeDataModel> = mutableListOf()
        val cursor: Cursor = dataBaseSelectedRecipes
                .rawQuery("SELECT * FROM selected_recipes", null)

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id: Double = cursor.getDouble(0)
                val urlImage: String = cursor.getString(1)
                val title: String = cursor.getString(2)
                val numberOfServings: Double = cursor.getDouble(3)
                val energy: Double = cursor.getDouble(4)
                val protein: Double = cursor.getDouble(5)
                val fat: Double = cursor.getDouble(6)
                val carbs: Double = cursor.getDouble(7)
                val listOfIngredientsDB: String = cursor.getString(8)
                val urlRecipe: String = cursor.getString(9)

                val listOfIngredients: MutableList<Ingredient> = getListofIngredients(listOfIngredientsDB)

                val recipe: RecipeDataModel = RecipeDataModel(id, urlImage, title, numberOfServings, energy,
                        protein, fat, carbs, listOfIngredients, urlRecipe)

                list.add(recipe)
            }
        }
        cursor.close()
        return list
    }

    private fun getListofIngredients(str: String): MutableList<Ingredient> {
        val list: MutableList<Ingredient> = mutableListOf()
        val jsonArray = JSONArray(str)
        for (item in 0 until jsonArray.length()) {
            val ingredient = with(jsonArray.getJSONObject(item)) {
                Ingredient(
                        id = getDouble("id"),
                        ingredient = getString("ingredient")
                )
            }
            list.add(ingredient)
        }
        return list
    }
}