package com.yandex.smur.marina.task6_async.async

import com.yandex.smur.marina.task6_async.Contact

interface RepositoryInterface {
    fun addContactsFromDataBase(): MutableList<Contact>
    fun editCountFromDataBase(contact: Contact)
    fun deleteContactFromDataBase(contact: Contact)
    fun addContactToDataBase(contact: Contact)
    fun closeThreads()
}