package com.yandex.smur.marina.myfinalproject.api

import com.yandex.smur.marina.myfinalproject.RecipeDataModel
import org.json.JSONObject

class RecipesDataModelMapper : (String) -> List<RecipeDataModel>{

    override fun invoke(jsonData: String): List<RecipeDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonHintsArray = jsonObject.getJSONArray("hits")
        if (jsonHintsArray.length() != 0) {
            val itemList = mutableListOf<RecipeDataModel>()
            for (index in 0 until jsonHintsArray.length()) {

                val recipeDataModel= with(jsonHintsArray.getJSONObject(index)) {
                     RecipeDataModel (
                            urlImage = getString("image"),
                            title = getString("label"),
                            numberOfServings = getInt("yield"),
                            energy = getInt("calories"),
                            protein = getInt("PROCNT"),
                            fat = getInt("FAT"),
                            carbs = getInt("CHOCDF"),
                            listOfIngredients = getJSONArray("ingredients"),
                            urlRecipe = getString("url")
                    )
                }
                itemList.add(recipeDataModel)
            }
            return itemList
        }
        return emptyList()
    }
}