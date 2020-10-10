package com.yandex.smur.marina.task8_weather

import org.json.JSONObject

class WeatherDataModelMapper : (String) -> List<WeatherDataModel> {

    override fun invoke(jsonData: String): List<WeatherDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonArticleArray = jsonObject.getJSONArray("hourly")
        if (jsonArticleArray.length() != 0) {
            val itemList = mutableListOf<WeatherDataModel>()
            for (index in 0 until jsonArticleArray.length()) {
                val weatherDataModel = with(jsonArticleArray.getJSONObject(index)) {
                    WeatherDataModel(
                            temperature = getString("temp"),
                            time = getString("dt")
//                            ,
//                            description = getString("description")
                    )
                }
                itemList.add(weatherDataModel)
            }
        }
        return emptyList()
    }

}