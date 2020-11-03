package com.yandex.smur.marina.task6_async.async

import com.yandex.smur.marina.task6_async.Contact

interface RepositoryInterface {
    fun addContactsFromDataBase(listener: DBListener<MutableList<Contact>>): MutableList<Contact>
    fun editCountFromDataBase(contact: Contact, listener: DBListener<Contact>)
    fun deleteContactFromDataBase(contact: Contact, listener: DBListener<Contact>)
    fun addContactToDataBase(contact: Contact, listener: DBListener<Contact>)
    fun closeThreads()
}