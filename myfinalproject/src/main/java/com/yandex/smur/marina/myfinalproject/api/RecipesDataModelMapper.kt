package com.yandex.smur.marina.myfinalproject.api

import android.util.Log
import org.json.JSONObject

class RecipesDataModelMapper : (String) -> List<RecipeDataModel> {

    override fun invoke(jsonData: String): List<RecipeDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonHintsArray = jsonObject.getJSONArray("hits")
        if (jsonHintsArray.length() != 0) {
            val itemList = mutableListOf<RecipeDataModel>()
            for (index in 0 until jsonHintsArray.length()) {
                val dataModel = with(jsonHintsArray.getJSONObject(index)) {
                    RecipeDataModel(
                            urlImage = getJSONObject("recipe").getString("image"),
                            title = getJSONObject("recipe").getString("label"),
                            numberOfServings = getJSONObject("recipe").getDouble("yield"),
                            energy = getJSONObject("recipe").getDouble("calories"),
//                            protein = getJSONObject("recipe").getJSONObject("PROCNT").getInt("quantity"),
//                            fat = getJSONObject("recipe").getInt("FAT"),
//                            carbs = getJSONObject("recipe").getInt("CHOCDF"),
//                            listOfIngredients = getJSONArray("ingredients"),
                            urlRecipe = getJSONObject("recipe").getString("url")
                    )
                }
                itemList.add(dataModel)
            }
            return itemList
            Log.d("ActivitySearchResult", itemList.toString())
        }
        return emptyList()
    }
}