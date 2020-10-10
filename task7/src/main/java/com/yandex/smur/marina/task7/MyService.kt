package com.yandex.smur.marina.task7

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Environment
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.io.File
import java.io.FileWriter

private const val FILE_NAME = "myLogs.txt"

class MyService : Service() {

    private val logger = Logger()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        createNotification(baseContext)
//        val textForLogger = intent.getStringExtra("log")
        val textForLogger = TextForLogger.textLogger
        if (textForLogger != null) {
            logger.writeMyLog("textForLogger")
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotification(context: Context) {
        val notification = NotificationCompat.Builder(context, "CHANNEL")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("SERVICE IS WORKING")
                .setContentText("SERVICE IS WORKING")
                .build()
        startForeground(10, notification)
    }

}

