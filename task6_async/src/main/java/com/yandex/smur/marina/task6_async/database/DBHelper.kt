package com.yandex.smur.marina.task6_async.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context)
    : SQLiteOpenHelper(context, "ContactPlus", null, 1) {
    private val contentValues: ContentValues = ContentValues()


    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ContactPlus (KEY_ID REAL PRIMARY KEY,KEY_NAME  TEXT, KEY_INFO TEXT, KEY_IMAGE  INTEGER)")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, p1: Int, p2: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ContactPlus")
        onCreate(sqLiteDatabase)
    }
}