package com.yandex.smur.marina.task7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("mLog", "Начинаю создававть BR")
       val action = intent.action
        val date = Calendar.getInstance().time!!
        val dateFormatter = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
        val dateForm = dateFormatter.format(date)
        when(action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED,
                Intent.ACTION_TIME_CHANGED,
                Intent.ACTION_SCREEN_OFF -> {
                val str = "$dateForm: $action"
                Log.d("mLog", str)
                startStorageService(context, str)
            }
        }
    }

    private fun startStorageService(context: Context, data: String){
        val intentService = Intent(context, MyService::class.java)
        intentService.putExtra("Log", data)
        context.startService(intentService)
        Log.d("mLog", "Запустила сервис")
    }

}