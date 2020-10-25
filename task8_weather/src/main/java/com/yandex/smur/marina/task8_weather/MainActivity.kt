package com.yandex.smur.marina.task8_weather

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yandex.smur.marina.task8_weather.setting.SettingFragment
import com.yandex.smur.marina.task8_weather.view.WeatherAtThisMomentFragment
import com.yandex.smur.marina.task8_weather.view.WeatherListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val weatherAtThisMomentFragment: WeatherAtThisMomentFragment by lazy { WeatherAtThisMomentFragment.getInstance() }
    private val weatherListFragment: WeatherListFragment by lazy { WeatherListFragment.getInstanc() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setWeatherAtThisMomentFragment()
        setWeatherListFragment()
        buttonSetting.setOnClickListener(this)
    }

    private fun showSettingFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerSetting, fragment, tag)
                .remove(weatherListFragment)
                .remove(weatherAtThisMomentFragment)
                .addToBackStack(null)
                .commit()
    }

    private fun setWeatherListFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerWeatherList, weatherListFragment, WeatherListFragment.TAG)
                .commit()
    }

    private fun setWeatherAtThisMomentFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerWeatherAtTisMoment, weatherAtThisMomentFragment, WeatherAtThisMomentFragment.TAG)
                .commit()
    }

    override fun onClick(view: View?) {
        when (view) {
            buttonSetting -> showSettingFragment(SettingFragment.getInstance(), SettingFragment.TAG)
        }

    }
}
