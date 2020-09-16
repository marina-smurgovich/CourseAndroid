package com.yandex.smur.marina.task5.room

//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class ContactViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val repository : ContactRepository
//    val allContact : LiveData<MutableList<Contact>>
//
//    init {
//        val contactDao = ContactDataBase.getContactDataBase(application).contactDao()
//        repository = ContactRepository(contactDao)
//        allContact = repository.allContacts
//    }
//
//    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
//        repository.insert(contact)
//    }
//}