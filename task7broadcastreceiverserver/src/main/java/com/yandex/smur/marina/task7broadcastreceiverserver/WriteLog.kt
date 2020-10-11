package com.yandex.smur.marina.task7broadcastreceiverserver

import android.content.Context
import android.os.Environment
import java.io.File

private const val FILE_NAME = "Logger.txt"

class WriteLog {

    private val fileDir = GetFileDir.fileDir
    private val file = File(fileDir, FILE_NAME)

    fun writeLog(text: String?) {
        val thread = Thread(Runnable {
            if (text != null) {
                file.appendText(text)
            }
        })
        thread.start()
    }
}

class GetFileDir constructor() {

    companion object {
        var fileDir: File = File("")

        fun getFileDir(context: Context, storageType: StorageType) {
            fileDir = context.filesDir
            if (storageType == StorageType.EXTERNAL) {
                fileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)!!
            }
        }
    }


}