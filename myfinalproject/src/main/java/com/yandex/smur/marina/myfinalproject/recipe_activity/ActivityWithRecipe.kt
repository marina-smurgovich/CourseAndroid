package com.yandex.smur.marina.myfinalproject.recipe_activity

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import com.yandex.smur.marina.myfinalproject.activity_web_page.ActivityWebPageWithRecipe
import com.yandex.smur.marina.myfinalproject.sqlite_database.DBHelper
import com.yandex.smur.marina.myfinalproject.sqlite_database.DBHelperSelectedIngredients

import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_with_recipe.*
import java.text.DecimalFormat

public const val REQUEST_CODE_ACTIVITYWITHRECIPE = "14568"

class ActivityWithRecipe : AppCompatActivity() {

    private lateinit var dataBaseSelectedRecipes: SQLiteDatabase
    private lateinit var databaseSelectedIngredients: SQLiteDatabase
    private lateinit var contentValues: ContentValues
    private lateinit var dbHelper: DBHelper
    private lateinit var dbHelperSelectedIngredients: DBHelperSelectedIngredients

    private lateinit var recipe: RecipeDataModel
//    private lateinit var selectedRecipeDao: SelectedRecipeDao

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

        seeTheFullRecipe(recipe.urlRecipe)

        addToFavourites(recipe)

        back()

    }

    private fun settingActivity() {
        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
        val navController = host.navController

        //включила боковое меню
        val sideBar = findViewById<NavigationView>(R.id.nav_view)
        sideBar?.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout = drawer_layout)
        val tooBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        tooBar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun seeTheFullRecipe(url: String) {
        buttonSeeTheFullRecipe.setOnClickListener {
            val intent = Intent(this@ActivityWithRecipe, ActivityWebPageWithRecipe::class.java)
            intent.putExtra("seeTheFullRecipe", url)
            startActivity(intent)
        }
    }

    private fun addToFavourites(recipe: RecipeDataModel) {
        buttonAddToFavourites.setOnClickListener {
            addRecipeToDataBase(recipe)
        }
    }

    private fun back() {
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun initIngredients(listOfIngredients: MutableList<Ingredient>) {
        listWithIngredients.apply {
            adapter = IngredientsAdapter(listOfIngredients, object : IngredientsAdapter.OnclickListenerAdapter{
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


    private fun listToJson (recipe: RecipeDataModel) : String {
        val list = recipe.listOfIngredients
        val inputArray = list.toTypedArray()

        val gson = Gson()
        val inputString = gson.toJson(inputArray)
        return inputString
    }

    fun addIngredientToShoppingList (ingredient : Ingredient) {
        contentValues.put("id", ingredient.id)
        contentValues.put("ingredient", ingredient.ingredient)

        databaseSelectedIngredients.insert("selected_ingredients", null, contentValues)
    }
}