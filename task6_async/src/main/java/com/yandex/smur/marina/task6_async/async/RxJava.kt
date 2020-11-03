package com.yandex.smur.marina.task6_async.async

import com.yandex.smur.marina.task6_async.Contact
import com.yandex.smur.marina.task6_async.database.DBHelper
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxJava(private val dbHelper: DBHelper) : RepositoryInterface {

    private lateinit var disposable: Disposable

    override fun addContactsFromDataBase(listener: DBListener<MutableList<Contact>>): MutableList<Contact> {
        disposable = Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<MutableList<Contact>> ->
            emitter.onNext(dbHelper.addContactsFromDataBase())
            emitter.onComplete()
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        return dbHelper.addContactsFromDataBase()
    }


    override fun editCountFromDataBase(contact: Contact, listener: DBListener<Contact>) {
        disposable = Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<Contact> ->
            dbHelper.editCountFromDataBase(contact)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    override fun deleteContactFromDataBase(contact: Contact, listener: DBListener<Contact>) {
        disposable = Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<Contact> ->
            dbHelper.deleteContactFromDataBase(contact)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    override fun addContactToDataBase(contact: Contact, listener: DBListener<Contact>) {
        disposable = Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<Contact> ->
            dbHelper.addContactToDataBase(contact)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    override fun closeThreads() {
        dbHelper.close()
        disposable.dispose()
    }


}