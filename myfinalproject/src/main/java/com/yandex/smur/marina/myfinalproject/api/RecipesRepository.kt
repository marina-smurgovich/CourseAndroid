package com.yandex.smur.marina.myfinalproject.api


import com.yandex.smur.marina.myfinalproject.RecipeDataModel
import io.reactivex.Single

interface RecipesRepository {
    fun getRecipes (searchingByKeyword: String) : Single<List <RecipeDataModel>>
}