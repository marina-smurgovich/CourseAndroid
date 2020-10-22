package com.yandex.smur.marina.task8_weather

import androidx.fragment.app.Fragment
import com.yandex.smur.marina.task8_weather.presenter.WeatherListItemModel
import com.yandex.smur.marina.task8_weather.presenter.WeatherListView

class WeatherAtThisMomentFragment : Fragment(), WeatherListView{

    private lateinit var units : String



    override fun showWeatherList(weatherList: List<WeatherListItemModel>) {
        TODO("Not yet implemented")
    }

    override fun onError(errorMessage: String) {
        TODO("Not yet implemented")
    }

}