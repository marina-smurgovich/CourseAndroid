package com.yandex.smur.marina.task8_weather.presenter

import android.util.Log
import com.yandex.smur.marina.task8_weather.model.WeatherDataModel
import com.yandex.smur.marina.task8_weather.model.WeatherRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class WeatherListItemPresenterImpl(
        private var weatherListView: WeatherListView?,
        private val weatherRepositoryImpl: WeatherRepositoryImpl,
        private val weatherItemListModelMapper: (List<WeatherDataModel>) -> List<WeatherListItemModel>,
) : WeatherListPresenter {

    private var disposable: Disposable? = null

    override fun fetchWeatherList(units: String) {
        disposable = weatherRepositoryImpl.getWeatherList(units)
                .map { weatherItemListModelMapper(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            weatherListView?.showWeatherList(list)
                            Log.d("mLog", list.toString())
                        },
                        { throwable ->
                            weatherListView?.onError(throwable.toString())
                            Log.d("mLog", throwable.toString())
                        })
    }

    override fun dispose() {
        disposable?.dispose()
        weatherListView = null
    }

}