package com.yandex.smur.marina.task8_weather.model

import android.util.Log
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherDataModelMapper : (String) -> List<WeatherDataModel> {

    override fun invoke(jsonData: String): List<WeatherDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonArticleArray = jsonObject.getJSONArray("list")
        if (jsonArticleArray.length() != 0) {
            val itemList = mutableListOf<WeatherDataModel>()
            for (index in 0 until jsonArticleArray.length()) {
                val weatherDataModel = with(jsonArticleArray.getJSONObject(index)) {
                    WeatherDataModel(
                            temperature = getJSONObject("main").getDouble("temp"),
                            time = timeFormatter(getString("dt_txt")),
                            description = getJSONArray("weather").getJSONObject(0).getString("description"),
                            urlImage = getImage(getJSONArray("weather").getJSONObject(0).getString("icon"))
                    )
                }
                itemList.add(weatherDataModel)
            }
            return itemList
            Log.d("mLog", itemList.toString())
        }
        return emptyList()
    }

    private fun timeFormatter(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(date, formatter)
        return "${dateTime.hour}:${dateTime.minute}"
    }

    private fun getImage(icon: String): String {
        return "http://openweathermap.org/img/wn/$icon@2x.png"
    }

}