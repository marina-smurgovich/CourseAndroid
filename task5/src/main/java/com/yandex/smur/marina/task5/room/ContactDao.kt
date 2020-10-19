//package com.yandex.smur.marina.task5.room
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.yandex.smur.marina.task5.Contact
//
//@Dao
//interface ContactDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(contact: Contact)
//
//    @Delete
//    fun delete(contact: Contact)
//
//    @Update
//    fun update(contact: Contact)
//
//    @Query("Select * from Contact")
//    fun gelAll(): LiveData<MutableList<Contact>>
//
//}