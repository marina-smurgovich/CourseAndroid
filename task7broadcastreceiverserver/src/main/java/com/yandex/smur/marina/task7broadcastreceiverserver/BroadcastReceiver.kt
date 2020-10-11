package com.yandex.smur.marina.task7broadcastreceiverserver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


private const val DATE_FORMAT = "yyyy/MM/dd HH:mm"

class BroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent!!.action
        val date = Calendar.getInstance().time
        val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
        val dateForLogger = dateFormatter.format(date)
        Log.d("MyLog", "начал работу onReceive $dateForLogger")
//        when (action) {
//            Intent.ACTION_AIRPLANE_MODE_CHANGED,
//            Intent.ACTION_TIME_CHANGED,
//            Intent.ACTION_SCREEN_OFF ->
//            {
        if (action != null) {
            startMyService(context, dateForLogger, action)
        }
//            }
        }
    }


private fun startMyService(context: Context, dateForLogger: String, action: String) {
    val intentForMyService = Intent(context, MyService::class.java)
    intentForMyService.putExtra("action", action)
    intentForMyService.putExtra("date", dateForLogger)
    context.startService(intentForMyService)
}
