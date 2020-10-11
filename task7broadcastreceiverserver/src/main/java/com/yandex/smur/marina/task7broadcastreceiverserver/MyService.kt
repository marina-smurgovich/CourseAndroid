package com.yandex.smur.marina.task7broadcastreceiverserver

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Environment
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MyService : Service() {

    private val fr = WriteLog()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        createNotification(baseContext)
        val action =  intent!!.getStringExtra("action")
        val date = intent.getStringExtra("date")
        val srt = action + " " + date
        fr.writeLog(srt)

        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(p0: Intent?): IBinder? = SimpleServiceBinder()


    inner class SimpleServiceBinder : Binder() {
        fun getService() = this@MyService
    }

    private fun createNotification(context: Context) {
        val notification = NotificationCompat.Builder(context, "CHANNEL")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Сервис")
                .setContentText("Сервис работает")
                .build()
        startForeground(10, notification)
    }
}