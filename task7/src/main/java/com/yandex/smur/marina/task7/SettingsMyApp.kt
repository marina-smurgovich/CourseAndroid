package com.yandex.smur.marina.task7

import android.content.Context
import android.content.SharedPreferences
import android.widget.RadioGroup

class SettingsMyApp private constructor() {
    companion object {

        private lateinit var sharedPreferences: SharedPreferences

        fun getSettings(context: Context): SettingsMyApp {
            sharedPreferences = context.getSharedPreferences("MySetting", Context.MODE_PRIVATE)
            return SettingsMyApp()
        }
    }

    fun saveSetting(radioGroup: RadioGroup) {
        sharedPreferences.edit().apply {
            putInt("MySetting", R.id.radioGroup)
            apply()
        }
    }

    fun setSetting(radioGroup: RadioGroup) {
        radioGroup.check(sharedPreferences.getInt("MySetting", 0))
    }
}