package com.yandex.smur.marina.myfinalproject.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel

//@Dao
//interface SelectedRecipeDao {
//
//    @Query("SELECT * FROM selected_recipes")
//    fun getAllRecipes() : LiveData<List<RecipeDataModel>>
//
//    @Query("SELECT * FROM selected_recipes WHERE id LIKE id")
//    fun getSelectedRecipe(id : RecipeDataModel) : RecipeDataModel
//
//    @Query("DELETE FROM selected_recipes")
//    fun deleteAll()
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(recipe : RecipeDataModel)
//
//    @Update
//    fun update(recipe: RecipeDataModel)
//
//    @Delete
//    fun delete(recipe: RecipeDataModel)
//
//
//}