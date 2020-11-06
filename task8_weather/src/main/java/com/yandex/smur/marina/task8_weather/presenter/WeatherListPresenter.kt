package com.yandex.smur.marina.task8_weather.presenter

interface WeatherListPresenter {
    fun fetchWeatherList(units : String)
    fun dispose()
}