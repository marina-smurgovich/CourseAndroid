package com.yandex.smur.marina.myfinalproject.selected

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import com.yandex.smur.marina.myfinalproject.recipe_activity.ActivityWithRecipe
import com.yandex.smur.marina.myfinalproject.recipe_activity.Ingredient
import com.yandex.smur.marina.myfinalproject.sqlite_database.DBHelper
import kotlinx.android.synthetic.main.fragment_selected.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

class SelectedFragment : Fragment() {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var databaseSelectedRecipes: SQLiteDatabase
    private lateinit var contentValues: ContentValues
    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: SelectedAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_selected, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHelper = DBHelper(activity!!)
        databaseSelectedRecipes = dbHelper.writableDatabase
        contentValues = ContentValues()


        val listOfSelectedRecipes: MutableList<RecipeDataModel> = getSelectedRecipesFromDB()

        recyclerViewSelected.apply {
            adapter = SelectedAdapter(listOfSelectedRecipes, object : SelectedAdapter.OnclickListenerAdapter {
                override fun onItemClick(recipe: RecipeDataModel) {
                    val intent = Intent(activity, ActivityWithRecipe::class.java)
                    intent.putExtra("recipe", recipe)
                    startActivity(intent)
                }

            }, object : SelectedAdapter.OnClickListenerButton {
                override fun onItemClick(recipe: RecipeDataModel) {
                    deleteRecipe(recipe)
                    updateSelectedListAfterRemoveRecipe(recipe)
                }
            })
            viewManager = LinearLayoutManager(activity)
        }

        buttonRemoveAllInSelected.setOnClickListener{
            deleteAllSelectedList()
            updateAfterDeleteAllSelectedList()
        }

    }

    private fun getSelectedRecipesFromDB(): MutableList<RecipeDataModel>{
        val list: MutableList<RecipeDataModel> = mutableListOf()
        val cursor : Cursor = databaseSelectedRecipes
                .rawQuery("SELECT * FROM selected_recipes", null)

        if (cursor!=null) {
            while(cursor.moveToNext()) {
                val id: Double = cursor.getDouble(0)
                val urlImage: String = cursor.getString(1)
                val title: String = cursor.getString(2)
                val numberOfServings: Double = cursor.getDouble(3)
                val energy: Double = cursor.getDouble(4)
                val protein: Double = cursor.getDouble(5)
                val fat: Double = cursor.getDouble(6)
                val carbs: Double = cursor.getDouble(7)
                val listOfIngredientsDB : String = cursor.getString(8)
                val urlRecipe: String = cursor.getString(9)

                val listOfIngredients : MutableList<Ingredient> = getListofIngredients(listOfIngredientsDB)

                val recipe : RecipeDataModel = RecipeDataModel(id, urlImage, title, numberOfServings, energy,
                        protein, fat, carbs, listOfIngredients, urlRecipe)

                list.add(recipe)
            }
        }
        cursor.close()
        return list
    }

    private fun getListofIngredients(str: String): MutableList<Ingredient> {
       val list : MutableList<Ingredient> = mutableListOf()
//        val jsonArray = JSONArray(str)
//        for (index in 0 until jsonArray.length()) {
//            val ingredient : Ingredient = Ingredient(Math.random(), jsonArray.get(index).toString())
//            list.add(ingredient)
//        }
//        val gson = Gson()
//        val type = object : TypeToken<MutableList<Ingredient>>() {}.type
//        val ingredients : MutableList<Ingredient> = gson.fromJson(str, type)
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

    private fun deleteRecipe(recipe: RecipeDataModel) {
        val id = recipe.id
        databaseSelectedRecipes.delete("selected_recipes", "id = " + id, null)
    }

    private fun updateSelectedListAfterRemoveRecipe(recipe: RecipeDataModel){
        adapter =  recyclerViewSelected.adapter as SelectedAdapter
        adapter.deleteRecipe(recipe)
    }

    private fun deleteAllSelectedList(){
       databaseSelectedRecipes.delete("selected_recipes", null, null)
    }

    private fun updateAfterDeleteAllSelectedList(){
        adapter =  recyclerViewSelected.adapter as SelectedAdapter
        adapter.deleteallRecipe()
    }
}