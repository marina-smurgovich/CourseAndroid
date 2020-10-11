package com.yandex.smur.marina.myfinalproject

import java.io.Serializable

data class Recipe(
        val id: Int = Math.random() as Int,
        val urlImage: String,
        val title: String,
        val numberOfServings: Int,
        val energy: Int,
        val protein : Int,
        val fat : Int,
        val carbs: Int,
        val listofIngredients : MutableList<Ingredients>,
        val urlRecipe : String
) : Serializable {

}
