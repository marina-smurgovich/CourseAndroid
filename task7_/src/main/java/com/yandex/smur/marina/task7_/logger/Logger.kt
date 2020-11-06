package com.yandex.smur.marina.task7_.logger

import android.content.Context
import android.os.Environment
import android.widget.EditText
import com.yandex.smur.marina.task7_.StorageType
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter

class Logger {
    private val fileDirectory = FileDirectory.fileDirectory
    private val file = File(fileDirectory, "LoggerTask7.txt")

    fun writeLog(log: String) {
        val thread = Thread(Runnable {
            val fileWriter: FileOutputStream = FileOutputStream(file, true)
            fileWriter.write(log.toByteArray())
            fileWriter.close()
        })
        thread.start()
    }
}

class FileDirectory private constructor() {

    companion object {
        var fileDirectory: File = File("")

        fun getileDirectory(context: Context, storageType: StorageType) {
            fileDirectory = context.filesDir
            if (storageType == StorageType.EXTERNAL) {
                fileDirectory = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)!!
            }
        }
    }

}