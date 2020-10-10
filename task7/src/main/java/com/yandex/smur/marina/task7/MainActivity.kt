package com.yandex.smur.marina.task7

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mySettingsMyApp by lazy { SettingsMyApp.getSettings(this) }
    private val broadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mySettingsMyApp.setSetting(radioGroup)
        registerReceiver(broadcastReceiver, createReceiver())
        getStorageType()
        radioGroup.setOnCheckedChangeListener { viewGroupButton, viewButton ->
            getStorageType()
            mySettingsMyApp.saveSetting(radioGroup)

        }

    }

//    override fun onResume() {
//        super.onResume()
//        mySettingsMyApp.setSetting(radioGroup)
//        registerReceiver(broadcastReceiver, createReceiver())
//        getStorageType()
//    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
        stopService(Intent(this, MyService::class.java))
    }

    private fun getStorageType() {
        val storageType = if (internal.isChecked) {
            StorageType.INTERNAL
        } else {
            StorageType.EXTERNAL
        }
        CreateFileDirectory.getFileDir(this, storageType)
    }


    private fun createReceiver() : IntentFilter {
        return IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
    }

}
