package com.yandex.smur.marina.task6_async.async

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Looper
import android.os.Message
import android.util.Log
import com.yandex.smur.marina.task6_async.Contact
import com.yandex.smur.marina.task6_async.ContactListAdapter
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.logging.Handler

class ThreadPoolExecutorHandler(
        private var contacts: MutableList<Contact>,
        private val dataBase: SQLiteDatabase,
        private val adapter: ContactListAdapter
) : RepositoryInterface {

    private val threadPoolExecutor: ThreadPoolExecutor = Executors.newFixedThreadPool(1) as ThreadPoolExecutor

    private val handler: android.os.Handler = android.os.Handler(Looper.getMainLooper(), object : android.os.Handler.Callback {
        override fun handleMessage(message: Message): Boolean {
            if (message.what == 0) run {
                val contacts: MutableList<Contact> = message.obj as MutableList<Contact>
                adapter.setContacts(contacts)
            }
            return false
        }
    })

    override fun addContactsFromDataBase(): MutableList<Contact> {
        threadPoolExecutor.execute(object : Runnable {
            override fun run() {
                contacts = addContactsFromDataBase()
                handler.sendMessage(handler.obtainMessage(0, contacts))
            }

        })
        return contacts
    }

    override fun editCountFromDataBase(contact: Contact) {
        threadPoolExecutor.execute(object : Runnable {
            override fun run() {
                val contentValues: ContentValues = ContentValues()
                contentValues.put("KEY_ID", contact.id)
                contentValues.put("KEY_NAME", contact.name)
                contentValues.put("KEY_INFO", contact.info)
                contentValues.put("KEY_IMAGE", contact.image)

                dataBase.update("ContactPlus", contentValues, "KEY_ID =? ", arrayOf(contact.id.toString()))
            }
        })
    }

    override fun deleteContactFromDataBase(contact: Contact) {
        threadPoolExecutor.execute(object : Runnable {
            override fun run() {
                val id: Double = contact.id

                dataBase.delete("ContactPlus", "KEY_ID = " + id, null)
            }
        })
    }

    override fun addContactToDataBase(contact: Contact) {
        threadPoolExecutor.execute(object : Runnable {
            override fun run() {
                val contentValues: ContentValues = ContentValues()
                contentValues.put("KEY_ID", contact.id)
                contentValues.put("KEY_NAME", contact.name)
                contentValues.put("KEY_INFO", contact.info)
                contentValues.put("KEY_IMAGE", contact.image)

                dataBase.insert("ContactPlus", null, contentValues)
                Log.d("mLog", " added Contact with id " + contact.id + " , name " + contact.name
                        + " , info " + contact.info + " , image " + contact.image)
            }
        })
    }

    override fun closeThreads() {
        threadPoolExecutor.shutdown()
    }
}