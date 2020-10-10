package com.yandex.smur.marina.task7

import android.content.Context
import android.os.Environment
import java.io.File

class CreateFileDirectory private constructor(){

    companion object{
        var fileDir: File = File("")

        fun getFileDir(context: Context, storageType: StorageType) {
            fileDir = context.filesDir
            if (storageType == StorageType.EXTERNAL) {
                fileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!
            }
        }
    }
}