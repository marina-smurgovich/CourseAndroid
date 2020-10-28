package com.yandex.smur.marina.task7

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.Environment
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.yandex.smur.marina.task7.logger.Logger
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException


class MyService : Service() {

    private val logger = Logger()

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val textForMyLogger = intent?.getStringExtra("Log")
        logger.writeLog("ПРОВЕРКА!!!!!!!!!")
        return super.onStartCommand(intent, flags, startId)
    }
}

