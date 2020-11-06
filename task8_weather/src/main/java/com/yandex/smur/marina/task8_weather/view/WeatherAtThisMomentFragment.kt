package com.yandex.smur.marina.task8_weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.yandex.smur.marina.task8_weather.R
import com.yandex.smur.marina.task8_weather.model.WeatherDataModelMapper
import com.yandex.smur.marina.task8_weather.model.WeatherRepositoryImpl
import com.yandex.smur.marina.task8_weather.presenter.WeatherItemListModelMapper
import com.yandex.smur.marina.task8_weather.presenter.WeatherListItemModel
import com.yandex.smur.marina.task8_weather.presenter.WeatherListItemPresenterImpl
import com.yandex.smur.marina.task8_weather.presenter.WeatherListView
import com.yandex.smur.marina.task8_weather.setting.Setting
import kotlinx.android.synthetic.main.fragment_weather_at_this_moment.*
import okhttp3.OkHttpClient

class WeatherAtThisMomentFragment : Fragment(), WeatherListView {

    private lateinit var units: String
    private val setting = Setting.getInstance()

    private val presenterImp: WeatherListItemPresenterImpl = WeatherListItemPresenterImpl(
            weatherListView = this,
            weatherRepositoryImpl = WeatherRepositoryImpl(OkHttpClient(), WeatherDataModelMapper()),
            weatherItemListModelMapper = WeatherItemListModelMapper()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_at_this_moment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setting.getSharedPreferences(activity!!.applicationContext)
        setUnits()
        presenterImp.fetchWeatherList(units)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterImp.dispose()
    }

    private fun setUnits() {
        units = if (setting.loadSetting()) {
            "metric"
        } else "imperial"
    }

    override fun showWeatherList(weatherList: List<WeatherListItemModel>) {
        Glide.with(context!!)
                .load(weatherList[0].urlImage)
                .into(imageViewWeather)
        textViewCityName.text = "Minsk"
        textViewDegrees.text = tempConvert(weatherList[0].temperature.toString(), units)
        textViewWeather.text = weatherList[0].description
    }

    override fun onError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val TAG = "WeatherAtThisMomentFragment"
        fun getInstance() = WeatherAtThisMomentFragment()
    }

    private fun tempConvert(temp: String, units: String): String = if (units == "metric") "$temp °C" else "$temp °F"
}