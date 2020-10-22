package com.yandex.smur.marina.task8_weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yandex.smur.marina.task8_weather.model.WeatherDataModelMapper
import com.yandex.smur.marina.task8_weather.model.WeatherRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposable = WeatherRepositoryImpl(
                okHttpClient = OkHttpClient(),
                weatherDataModelMapper = WeatherDataModelMapper()
        ).getWeatherRepository(
//                "53.9000000", "27.5666700"
        )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list -> Log.d("MainActivity", list.toString()) },
                        { throwable -> Log.d("MainActivity", throwable.toString()) }
                )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
