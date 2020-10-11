package com.yandex.smur.marina.myfinalproject

import org.json.JSONArray
import java.io.Serializable

data class RecipeDataModel(
//        val id: Int = Math.random() as Int,
        val urlImage: String,
        val title: String,
        val numberOfServings: Int,
        val energy: Int,
        val protein: Int,
        val fat: Int,
        val carbs: Int,
        val listOfIngredients: JSONArray,
        val urlRecipe: String
) : Serializable {

}
