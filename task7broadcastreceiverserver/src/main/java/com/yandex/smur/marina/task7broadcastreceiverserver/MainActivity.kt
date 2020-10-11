package com.yandex.smur.marina.task7broadcastreceiverserver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yandex.smur.marina.task7broadcastreceiverserver.GetFileDir.Companion.getFileDir

class MainActivity : AppCompatActivity() {

    private val broadcastReceiver = BroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLog", "broadcastReceiver")
        getFileDir(this, StorageType.INTERNAL)
        createReceiver()
    }

    private fun createReceiver() {
        Log.d("MyLog", "broadcastReceivercreateReceiver start")
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        Log.d("MyLog", "before registerReceiver")
        registerReceiver(broadcastReceiver, intentFilter)
        Log.d("MyLog", "after registerReceiver")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}
