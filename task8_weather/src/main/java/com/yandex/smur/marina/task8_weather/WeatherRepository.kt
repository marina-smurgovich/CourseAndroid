package com.yandex.smur.marina.task8_weather

import io.reactivex.Single


interface WeatherRepository {
    fun getWeatherRepository (
//            lat : String, lon : String
    ) : Single<List<WeatherDataModel>>
}