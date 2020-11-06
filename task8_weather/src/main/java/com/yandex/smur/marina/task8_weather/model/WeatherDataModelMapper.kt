package com.yandex.smur.marina.task8_weather.model

import org.json.JSONObject
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class WeatherDataModelMapper : (String) -> List<WeatherDataModel> {

    override fun invoke(jsonData: String): List<WeatherDataModel> {
        val jsonObject = JSONObject(jsonData)
        val jsonArticleArray = jsonObject.getJSONArray("hourly")
        if (jsonArticleArray.length() != 0) {
            val itemList = mutableListOf<WeatherDataModel>()
            for (index in 0 until jsonArticleArray.length()) {
                val weatherDataModel = with(jsonArticleArray.getJSONObject(index)) {
                    WeatherDataModel(
                            temperature = temperatureFormatter(getDouble("temp")),
                            time = timeFormatter(getLong("dt")),
                            description = getJSONArray("weather").getJSONObject(0).getString("main"),
                            urlImage = getImage(getJSONArray("weather").getJSONObject(0).getString("icon"))
                    )
                }
                itemList.add(weatherDataModel)
            }
            return itemList
        }
        return emptyList()
    }

    private fun timeFormatter(date: Long): String {
        val dateStr = SimpleDateFormat("HH:mm").format(Date(date as Long * 1000))
        return dateStr
    }

    private fun temperatureFormatter(temperature: Double): String {
        return DecimalFormat("#0").format(temperature)
    }

    private fun getImage(icon: String): String {
        return "http://openweathermap.org/img/wn/$icon@2x.png"
    }

}