package com.yandex.smur.marina.task7_broadcastreceiver_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "yyyy-MM-dd HH:mm"

class MyBroadcastReceiver :  BroadcastReceiver (){
    override fun onReceive(context: Context, intent: Intent) {

        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
        val now = Date()
        val date = simpleDateFormat.format(now)

        val action = intent.action

//        val str = "$date : ${action.toString()}"
//
//        val intentforService = Intent(context, MyService::class.java)
//        intentforService.putExtra("service", str)
//        context.startService(intentforService)

        when (action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED,
            Intent.ACTION_TIMEZONE_CHANGED,
            Intent.ACTION_SCREEN_OFF -> {
                srartMuService (context, date, action)
            }
        }
    }

    private fun srartMuService(context: Context, date: String, action: String) {
        val intentForService = Intent (context, MyService::class.java)
        intentForService.putExtra(KEY_FOR_ACTION, action)
        intentForService.putExtra(KEY_FOE_DATE, date)
        context.startService(intentForService)
    }

    companion object{
        const val KEY_FOR_ACTION = "KEY_FOR_ACTION"
        const val KEY_FOE_DATE = "KEY_FOE_DATE"
    }

}