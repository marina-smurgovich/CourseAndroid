package com.yandex.smur.marina.myfinalproject.api

import java.io.Serializable

data class RecipeDataModel(
        val id: Double = Math.random(),
        val urlImage: String,
        val title: String,
        val numberOfServings: Double,
        val energy: Double,
//        val protein: Int,
//        val fat: Int,
//        val carbs: Int,
//        val listOfIngredients: List<Ingredient>,
        val urlRecipe: String
)
    : Serializable {

}
