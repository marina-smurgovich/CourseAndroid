package com.yandex.smur.marina.task6_async.async

import com.yandex.smur.marina.task6_async.Contact
import com.yandex.smur.marina.task6_async.database.DBHelper
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
class ThreadPoolExecutorHandler(private val dbHelper: DBHelper) : RepositoryInterface {

    private val handler = android.os.Handler()
    val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, LinkedBlockingQueue())

    override fun addContactsFromDataBase(listener: DBListener<MutableList<Contact>>): MutableList<Contact> {
        val list: MutableList<Contact> = mutableListOf()
        threadPoolExecutor.submit(object : Runnable {
            override fun run() {
                list.addAll(dbHelper.addContactsFromDataBase())
            }
        })
        handler.post { listener.onDataReceived(list) }
        return list
    }

    override fun editCountFromDataBase(contact: Contact, listener: DBListener<Contact>) {
        threadPoolExecutor.submit { dbHelper.editCountFromDataBase(contact) }
        handler.post { listener.onDataReceived(contact) }
    }

    override fun deleteContactFromDataBase(contact: Contact, listener: DBListener<Contact>) {
        threadPoolExecutor.submit { dbHelper.deleteContactFromDataBase(contact) }
        handler.post { listener.onDataReceived(contact) }
    }

    override fun addContactToDataBase(contact: Contact, listener: DBListener<Contact>) {
        threadPoolExecutor.submit { dbHelper.addContactToDataBase(contact) }
        handler.post { listener.onDataReceived(contact) }
    }

    override fun closeThreads() {
        dbHelper.close()
    }

}