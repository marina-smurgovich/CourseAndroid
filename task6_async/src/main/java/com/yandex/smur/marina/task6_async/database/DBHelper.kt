package com.yandex.smur.marina.task6_async.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.yandex.smur.marina.task6_async.Contact

class DBHelper(context: Context)
    : SQLiteOpenHelper(context, "ContactPlus", null, 1) {
    private val contentValues: ContentValues = ContentValues()

    val dataBase = writableDatabase
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ContactPlus (KEY_ID REAL PRIMARY KEY,KEY_NAME  TEXT, KEY_INFO TEXT, KEY_IMAGE  INTEGER)")
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, p1: Int, p2: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ContactPlus")
        onCreate(sqLiteDatabase)
    }

    fun addContactToDataBase(contact: Contact) {
        contentValues.put("KEY_ID", contact.id)
        contentValues.put("KEY_NAME", contact.name)
        contentValues.put("KEY_INFO", contact.info)
        contentValues.put("KEY_IMAGE", contact.image)

        dataBase.insert("ContactPlus", null, contentValues)
        Log.d("mLog", " added Contact with id " + contact.id + " , name " + contact.name
                + " , info " + contact.info + " , image " + contact.image)
    }

    fun deleteContactFromDataBase(contact: Contact) {
        val id: Double = contact.id

        dataBase.delete("ContactPlus", "KEY_ID = " + id, null)
    }

    fun editCountFromDataBase(contact: Contact) {
        contentValues.put("KEY_ID", contact.id)
        contentValues.put("KEY_NAME", contact.name)
        contentValues.put("KEY_INFO", contact.info)
        contentValues.put("KEY_IMAGE", contact.image)

        dataBase.update("ContactPlus", contentValues, "KEY_ID =? ", arrayOf(contact.id.toString()))
    }

    fun addContactsFromDataBase(): MutableList<Contact> {
        val contacts: MutableList<Contact> = ArrayList()
        val cursor: Cursor = dataBase.rawQuery("SELECT * FROM ContactPlus", null)

        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                val idCursor: Double = cursor.getDouble(0)
                val nameCursor: String = cursor.getString(1)
                val infoCursor: String = cursor.getString(2)
                val imageCursor: Int = cursor.getInt(3)

                val contact = Contact(idCursor, nameCursor, infoCursor, imageCursor)
                contacts.add(contact)
            }
        }
        cursor.close()
        return contacts
    }

    override fun close() {
        super.close()
        dataBase.close()
    }
}