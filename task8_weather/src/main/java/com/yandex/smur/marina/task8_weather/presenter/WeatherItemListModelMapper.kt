package com.yandex.smur.marina.task8_weather.presenter

import com.yandex.smur.marina.task8_weather.model.WeatherDataModel

class WeatherItemListModelMapper : (List<WeatherDataModel>) -> List<WeatherListItemModel> {
    override fun invoke(weatherDataModel: List<WeatherDataModel>): List<WeatherListItemModel> =
            weatherDataModel.map { weatherDataModel ->
                WeatherListItemModel(
                        temperature = weatherDataModel.temperature,
                        time = weatherDataModel.time,
                        description = weatherDataModel.description,
                        urlImage = weatherDataModel.urlImage
                )
            }

}