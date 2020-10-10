package com.yandex.smur.marina.task7

import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "yyyy/MM/dd HH:mm"

class TextForLogger private constructor(){
    companion object {
        var textLogger: String? = null

        fun getLoggerText(date: Date, intent: Intent?) {
            val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
            val dateText = dateFormatter.format(date)
            textLogger = "$dateText - ${intent?.action.toString()} \n"
        }

    }
}