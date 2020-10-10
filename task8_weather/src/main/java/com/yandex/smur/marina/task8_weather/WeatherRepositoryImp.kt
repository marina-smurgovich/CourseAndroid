package com.yandex.smur.marina.task8_weather

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import okhttp3.*
import java.io.IOException
import java.lang.NullPointerException

private const val API_KEY = "48eab4d36c53ea2c6449fcc76285a962"

//https://api.openweathermap.org/data/2.5/onecall?lat=53.9000000&lon=27.5666700&exclude=daily&lang=ru&appid=$API_KEY

class WeatherRepositoryImpl(
        private val okHttpClient:OkHttpClient,
        private val weatherDataModelMapper: (String) -> List<WeatherDataModel>
) : WeatherRepository {


    override fun getWeatherRepository(
//            lat: String, lon: String
    ): Single<List<WeatherDataModel>> {
//        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lon&exclude=daily&lang=ru&appid=$API_KEY"
        val url = "https://api.openweathermap.org/data/2.5/onecall?lat=53.9000000&lon=27.5666700&exclude=daily&lang=ru&appid=48eab4d36c53ea2c6449fcc76285a962"

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