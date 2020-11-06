package com.yandex.smur.marina.task6_async.async

import com.yandex.smur.marina.task6_async.Contact
import com.yandex.smur.marina.task6_async.database.DBHelper
import java.util.concurrent.CompletableFuture
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class CompletableFutureThreadPoolExecutor(private val dbHelper: DBHelper) : RepositoryInterface {

    val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, LinkedBlockingQueue())

    override fun addContactsFromDataBase(listener: DBListener<MutableList<Contact>>): MutableList<Contact> {
        val list: MutableList<Contact> = mutableListOf()
        val completableFuture: CompletableFuture<Void> = CompletableFuture.runAsync(object : Runnable {
            override fun run() {
                list.addAll(dbHelper.addContactsFromDataBase())
            }
        }, threadPoolExecutor)
        completableFuture.thenAccept(Consumer { result -> listener.onDataReceived(list) })
        completableFuture.get()

        return list
    }

    override fun editCountFromDataBase(contact: Contact, listener: DBListener<Contact>) {
        val completableFuture: CompletableFuture<Void> = CompletableFuture.runAsync(object : Runnable {
            override fun run() {
                dbHelper.editCountFromDataBase(contact)
            }
        }, threadPoolExecutor)
        completableFuture.thenAccept(Consumer { result -> listener.onDataReceived(contact) })
        completableFuture.get()
    }

    override fun deleteContactFromDataBase(contact: Contact, listener: DBListener<Contact>) {
        val completableFuture: CompletableFuture<Void> = CompletableFuture.runAsync(object : Runnable {
            override fun run() {
                dbHelper.deleteContactFromDataBase(contact)
            }
        }, threadPoolExecutor)
        completableFuture.thenAccept(Consumer { result -> listener.onDataReceived(contact) })
        completableFuture.get()
    }

    override fun addContactToDataBase(contact: Contact, listener: DBListener<Contact>) {
        val completableFuture: CompletableFuture<Void> = CompletableFuture.runAsync(object : Runnable {
            override fun run() {
                dbHelper.addContactToDataBase(contact)
            }
        }, threadPoolExecutor)
        completableFuture.thenAccept(Consumer { result -> listener.onDataReceived(contact) })
        completableFuture.get()
    }

    override fun closeThreads() {
        dbHelper.close()
    }


}