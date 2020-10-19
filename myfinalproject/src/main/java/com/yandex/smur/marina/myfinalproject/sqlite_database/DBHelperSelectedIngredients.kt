package com.yandex.smur.marina.myfinalproject.sqlite_database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperSelectedIngredients(context: Context) : SQLiteOpenHelper(context, "selected_ingredients", null, 1) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE selected_ingredients (id REAL PRIMARY KEY, ingredient TEXT)")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, p1: Int, p2: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS selected_ingredients")
        onCreate(sqLiteDatabase)
    }
}