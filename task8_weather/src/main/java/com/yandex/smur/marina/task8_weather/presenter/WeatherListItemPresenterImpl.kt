package com.yandex.smur.marina.task8_weather.presenter

import com.yandex.smur.marina.task8_weather.model.WeatherDataModel
import com.yandex.smur.marina.task8_weather.model.WeatherRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class WeatherListItemPresenterImpl (
        private var weatherListView: WeatherListView?,
        private val weatherRepository: WeatherRepository,
        private val weatherItemListModelMapper: (List<WeatherDataModel>) -> List<WeatherListItemModel>
) : WeatherListPresenter{

    private var disposable: Disposable? = null

    override fun fetchWeatherList(units: String) {
       disposable = weatherRepository.getWeatherList(units)
               .map { weatherItemListModelMapper(it) }
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       {list -> weatherListView?.showWeatherList(list)},
                       {throwable -> weatherListView?.onError(throwable.toString())})
    }

    override fun dispose() {
       disposable?.dispose()
        weatherListView = null
    }

}