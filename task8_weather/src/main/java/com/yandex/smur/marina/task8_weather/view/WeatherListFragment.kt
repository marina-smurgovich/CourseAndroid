package com.yandex.smur.marina.task8_weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.task8_weather.R
import com.yandex.smur.marina.task8_weather.model.WeatherDataModelMapper
import com.yandex.smur.marina.task8_weather.model.WeatherRepositoryImpl
import com.yandex.smur.marina.task8_weather.presenter.WeatherItemListModelMapper
import com.yandex.smur.marina.task8_weather.presenter.WeatherListItemModel
import com.yandex.smur.marina.task8_weather.presenter.WeatherListItemPresenterImpl
import com.yandex.smur.marina.task8_weather.presenter.WeatherListView
import com.yandex.smur.marina.task8_weather.setting.Setting
import kotlinx.android.synthetic.main.fragment_weather_list.*
import okhttp3.OkHttpClient

class WeatherListFragment : Fragment(), WeatherListView {

    private lateinit var units: String
    private val setting = Setting.getInstance()
    private lateinit var adapter: WeatherListAdapter

    private val presenterImp: WeatherListItemPresenterImpl = WeatherListItemPresenterImpl(
            weatherListView = this,
            weatherRepositoryImpl = WeatherRepositoryImpl(OkHttpClient(), WeatherDataModelMapper()),
            weatherItemListModelMapper = WeatherItemListModelMapper()
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setting.getSharedPreferences(activity!!.applicationContext)
        recyclerViewWeatherList.apply {
            adapter = WeatherListAdapter()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setUnits()
        }
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
        adapter = recyclerViewWeatherList.adapter as WeatherListAdapter
        adapter.updateWeatherList(weatherList, units)
    }

    override fun onError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }


    companion object {
        const val TAG = "WeatherListFragment"
        fun getInstanc() = WeatherListFragment()
    }
}