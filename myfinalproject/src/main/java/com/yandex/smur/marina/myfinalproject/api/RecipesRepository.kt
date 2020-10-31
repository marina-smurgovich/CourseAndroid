package com.yandex.smur.marina.myfinalproject.api


import com.yandex.smur.marina.myfinalproject.search_result.SearchObject
import io.reactivex.Single

interface RecipesRepository {
    fun getRecipes(
            searchObject: SearchObject,
    ): Single<List<RecipeDataModel>>
}