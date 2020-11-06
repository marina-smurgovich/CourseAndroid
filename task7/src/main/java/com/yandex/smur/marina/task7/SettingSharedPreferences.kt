package com.yandex.smur.marina.task7

import android.content.Context
import android.content.SharedPreferences
import android.widget.RadioGroup

class SettingSharedPreferences private constructor() {


    companion object{
        lateinit var sharedPreferences: SharedPreferences

        fun getInstance(context: Context) : SettingSharedPreferences {
            sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            return SettingSharedPreferences()
        }
    }

    fun saveSetting(radioGroup: RadioGroup) {
        sharedPreferences.edit().apply(){
            putInt("Settings", radioGroup.checkedRadioButtonId)
            apply()
        }
    }

    fun loadSettings(radioGroup: RadioGroup) = radioGroup.check(sharedPreferences.getInt("Settings", 0))
}