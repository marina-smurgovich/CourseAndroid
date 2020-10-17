package com.yandex.smur.marina.myfinalproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel

@Dao
interface RecipeDao {

    @Query("SELECT * FROM Recipes")
    fun getAllRecipes(): LiveData<List<RecipeDataModel>>?

    @Query("SELECT * FROM Recipes WHERE id = idRecipes")
    fun getRecipe(idRecipe : Int): RecipeDataModel?

    @Insert
    fun insertRecipe (recipe : RecipeDataModel?)

    @Update
    fun updateRecipes(recipe : RecipeDataModel?)

    @Delete
    fun deleteContact(recipe: RecipeDataModel?)
}