package com.yandex.smur.marina.task7

import java.io.File

private const val FILE_NAME = "myLogs.txt"

class Logger {

    private val fileDirectory = CreateFileDirectory.fileDir
    private val file = File(fileDirectory,  FILE_NAME)

    fun writeMyLog (textForLogger:String) {
        val thread = Thread(Runnable {
            file.appendText(textForLogger)
        })
        thread.start()
    }
}