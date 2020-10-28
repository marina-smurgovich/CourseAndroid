package com.yandex.smur.marina.task7

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import com.yandex.smur.marina.task7.logger.FileDirectory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val broadcastReceiver by lazy { MyBroadcastReceiver() }
    private val setting by lazy { SettingSharedPreferences.getInstance(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setting.loadSettings(radioGroup)
        radioGroup.setOnCheckedChangeListener { radioGroup, radioButton ->
            getStorageType()
            setting.saveSetting(radioGroup)
            registerReceiver(broadcastReceiver, createReceiver())
        }
    }

    private fun getStorageType() {
        val storageType =
                if (internal.isChecked) {
                    StorageType.INTERNAL
                } else {
                    StorageType.EXTERNAL
                }
        FileDirectory.getileDirectory(this, storageType)
    }


    private fun createReceiver(): IntentFilter? {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        return intentFilter
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }


}
