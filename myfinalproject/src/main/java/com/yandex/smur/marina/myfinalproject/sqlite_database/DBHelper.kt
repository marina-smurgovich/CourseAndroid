package com.yandex.smur.marina.myfinalproject.sqlite_database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "selected_recipes", null, 1){

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE selected_recipes (id REAL PRIMARY KEY, urlImage  TEXT, title TEXT, numberOfServings REAL, energy REAL, protein REAL, fat REAL, carbs REAL, listOfIngredients TEXT, urlRecipe TEXT)")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, p1: Int, p2: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS selected_recipes")
        onCreate(sqLiteDatabase)
    }


}