package com.yandex.smur.marina.task7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

private const val DATE_FORMAT = "yyyy/MM/dd HH:mm"

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val date = Calendar.getInstance().time
//        val dateFormatter = SimpleDateFormat(DATE_FORMAT)
//        val dateStr = dateFormatter.format(date)
//        val textForLogger = "$dateStr: ${intent.action.toString()} \n"
//        when (intent.action) {
//            Intent.ACTION_AIRPLANE_MODE_CHANGED ->
//                startMyService(context, textForLogger, Intent.ACTION_AIRPLANE_MODE_CHANGED.toString() )
//            Intent.ACTION_TIME_CHANGED ->
//                startMyService(context, textForLogger, Intent.ACTION_TIME_CHANGED.toString())
//            Intent.ACTION_SCREEN_OFF ->
//                startMyService(context, textForLogger, Intent.ACTION_SCREEN_OFF.toString())
//        }
        TextForLogger.getLoggerText(date, intent)
        context.startService(Intent(context, MyService::class.java))
    }

//    private fun startMyService(context: Context, textForLogger: String, action: String) {
//        val intentForMyService = Intent(context, MyService::class.java)
//        intentForMyService.putExtra("log", textForLogger)
//        context.startService(intentForMyService)
//    }


}