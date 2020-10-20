package com.yandex.smur.marina.myfinalproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel

//@Database(entities = [RecipeDataModel::class], version = 1)
//abstract class SelectedRecipeDatabase :RoomDatabase() {
//
//    abstract fun getSelectedRecipeDao() : SelectedRecipeDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE : SelectedRecipeDatabase? = null
//
//        fun getDatabase (context: Context) : SelectedRecipeDatabase {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(context.applicationContext,
//                SelectedRecipeDatabase::class.java,
//                "selected_recipes")
//                        .allowMainThreadQueries()
//                        .build()
//            }
//            return INSTANCE as SelectedRecipeDatabase
//        }
//    }
//}