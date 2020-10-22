package com.yandex.smur.marina.task8_weather.presenter

interface WeatherListView {

    fun showWeatherList(weatherList: List<WeatherListItemModel>)
    fun onError(errorMessage: String)
}