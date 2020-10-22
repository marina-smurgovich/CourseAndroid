package com.yandex.smur.marina.task8_weather.model

import com.yandex.smur.marina.task8_weather.model.WeatherDataModel
import io.reactivex.Single


interface WeatherRepository {
    fun getWeatherList (units : String) : Single<List<WeatherDataModel>>
}