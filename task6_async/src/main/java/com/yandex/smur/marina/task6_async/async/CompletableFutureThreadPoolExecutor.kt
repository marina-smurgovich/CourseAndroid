package com.yandex.smur.marina.task6_async.async

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import com.yandex.smur.marina.task6_async.Contact
import com.yandex.smur.marina.task6_async.ContactListAdapter
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

class CompletableFutureThreadPoolExecutor(
        private var contacts: MutableList<Contact>,
        private val dataBase: SQLiteDatabase,
        private val adapter: ContactListAdapter,
) : RepositoryInterface{

    private val threadPoolExecutor: ThreadPoolExecutor = Executors.newFixedThreadPool(1) as ThreadPoolExecutor

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun addContactsFromDataBase(): MutableList<Contact> {
        CompletableFuture.runAsync({
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
        }, threadPoolExecutor)
        return contacts
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun editCountFromDataBase(contact: Contact) {
        CompletableFuture.runAsync({
            val contentValues: ContentValues = ContentValues()
            contentValues.put("KEY_ID", contact.id)
            contentValues.put("KEY_NAME", contact.name)
            contentValues.put("KEY_INFO", contact.info)
            contentValues.put("KEY_IMAGE", contact.image)

            dataBase.update("ContactPlus", contentValues, "KEY_ID =? ", arrayOf(contact.id.toString()))
        }, threadPoolExecutor)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun deleteContactFromDataBase(contact: Contact) {
        CompletableFuture.runAsync({
            val id: Double = contact.id

            dataBase.delete("ContactPlus", "KEY_ID = " + id, null)
        }, threadPoolExecutor)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun addContactToDataBase(contact: Contact) {
        CompletableFuture.runAsync({
            val contentValues: ContentValues = ContentValues()
            contentValues.put("KEY_ID", contact.id)
            contentValues.put("KEY_NAME", contact.name)
            contentValues.put("KEY_INFO", contact.info)
            contentValues.put("KEY_IMAGE", contact.image)

            dataBase.insert("ContactPlus", null, contentValues)
        }, threadPoolExecutor)
    }

    override fun closeThreads() {
        threadPoolExecutor.shutdown()
    }

}