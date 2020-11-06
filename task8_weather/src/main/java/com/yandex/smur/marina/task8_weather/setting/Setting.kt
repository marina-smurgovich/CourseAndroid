package com.yandex.smur.marina.task8_weather.setting

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.widget.SwitchCompat

class Setting private constructor() {

    private lateinit var sharedPreferences: SharedPreferences

    fun getSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE)
    }

    fun saveSetting(switchCompat: SwitchCompat) {
        sharedPreferences.edit().let {
            it.putBoolean("setting", switchCompat.isChecked)
            it.apply()
        }
    }

    fun setSetting(switchCompat: SwitchCompat) {
        switchCompat.isChecked = sharedPreferences.getBoolean("setting", false)
    }


    fun loadSetting(): Boolean = sharedPreferences.getBoolean("setting", false)

    companion object {
        fun getInstance(): Setting = Setting()
    }
}