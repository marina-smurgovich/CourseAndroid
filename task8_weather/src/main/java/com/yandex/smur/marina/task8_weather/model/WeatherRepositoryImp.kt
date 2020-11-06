package com.yandex.smur.marina.task8_weather.model

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request

private const val API_KEY = "48eab4d36c53ea2c6449fcc76285a962"

class WeatherRepositoryImpl(
        private val okHttpClient: OkHttpClient,
        private val weatherDataModelMapper: (String) -> List<WeatherDataModel>,
) : WeatherRepository {


    override fun getWeatherList(units: String): Single<List<WeatherDataModel>> {
        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=53.9000000&lon=27.5666700&exclude=daily&units=$units&appid=$API_KEY"

        val request = Request.Builder()
                .url(url)
                .build()

        return Single.create<String> { emitter ->
            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) emitter.onError(Throwable("Server error code: ${response.code}"))
                if (response.body == null) emitter.onError(NullPointerException("Body is null!!!"))
                emitter.onSuccess(response.body!!.string())
            }
        }.subscribeOn(Schedulers.io())
                .map { jsonData -> weatherDataModelMapper(jsonData) }
    }
}