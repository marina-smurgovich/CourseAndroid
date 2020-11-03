package com.yandex.smur.marina.task7_broadcastreceiver_service


import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Environment
import android.os.IBinder
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MyService : Service() {

    private lateinit var sharedPreferences: SharedPreferences
    private val thread = Thread()
    private var storageType: StorageType = StorageType.INTERNAL
    private val FILE_NAME = "myLogsTask7"

    override fun onBind(p0: Intent?): IBinder? = null


    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val action = intent.getStringExtra(MyBroadcastReceiver.KEY_FOR_ACTION)
        val date = intent.getStringExtra(MyBroadcastReceiver.KEY_FOE_DATE)
        writeLogToFile(date.toString(), action.toString(), startId)

        return super.onStartCommand(intent, flags, startId)
    }

    private fun writeLogToFile(date: String, action: String, startId: Int) {
        val getStorageType = loadStorageType()
        storageType = StorageTypeConverter.fromStringToStorageType(getStorageType.toString())
        thread.apply {
            Thread(Runnable {
                var fileDirectory: File? = filesDir
                if (storageType == StorageType.EXTERNAL) {
                    if (isExternalStorageWriteable()) {
                        fileDirectory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                    }
                    val file = File(fileDirectory, FILE_NAME)
                    val str = "$date: $action"
                    try {
                        FileOutputStream(file, true).apply {
                            write(str.toByteArray())
                            close()
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    stopSelf(startId)
                }
            }).start()
        }
    }

    private fun isExternalStorageWriteable(): Boolean = Environment.getExternalStorageState() == Environment.DIRECTORY_DOWNLOADS

    private fun loadStorageType(): String? {
        sharedPreferences = getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE)
        return sharedPreferences.getString(MainActivity.KEY_RADIOBUTTON_INDEX, StorageType.INTERNAL.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}