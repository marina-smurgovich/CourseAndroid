package com.yandex.smur.marina.task5.room

//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database (entities = [Contact::class], version = 1)
//abstract class ContactDataBase : RoomDatabase(){
//
//     abstract fun contactDao () : ContactDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE : ContactDataBase? = null
//
//        fun getContactDataBase (context : Context) : ContactDataBase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        ContactDataBase::class.java,
//                        "contactDataBase.db"
//                )
//                        .build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}