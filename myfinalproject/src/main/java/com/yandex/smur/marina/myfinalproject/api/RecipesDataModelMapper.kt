package com.yandex.smur.marina.myfinalproject.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONArray
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
                            protein = getJSONObject("recipe").getJSONObject("totalNutrients").getJSONObject("PROCNT").getDouble("quantity"),
                            fat = getJSONObject("recipe").getJSONObject("totalNutrients").getJSONObject("FAT").getDouble("quantity"),
                            carbs = getJSONObject("recipe").getJSONObject("totalNutrients").getJSONObject("CHOCDF").getDouble("quantity"),
                            listOfIngredients = pars(getJSONObject("recipe").getJSONArray("ingredientLines")),
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

    private fun pars (array : JSONArray) : MutableList<String> {
        val arrayP : MutableList<String> = mutableListOf()
        for (i in 0 until array.length()) {
            arrayP.add(array.get(i).toString())
        }
        return arrayP
    }
}