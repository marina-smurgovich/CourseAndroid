package com.yandex.smur.marina.task6_async.async

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import com.yandex.smur.marina.task6_async.Contact
import com.yandex.smur.marina.task6_async.ContactListAdapter
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxJava(
        private var contacts: MutableList<Contact>,
        private val dataBase: SQLiteDatabase,
        private val adapter: ContactListAdapter,
) : RepositoryInterface {

    private lateinit var disposable : Disposable

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun addContactsFromDataBase(): MutableList<Contact> {
        disposable = Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<MutableList<Contact>> ->
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
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ()

        return contacts
    }

    override fun editCountFromDataBase(contact: Contact) {
        disposable = Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<MutableList<Contact>> ->
            val contentValues: ContentValues = ContentValues()
            contentValues.put("KEY_ID", contact.id)
            contentValues.put("KEY_NAME", contact.name)
            contentValues.put("KEY_INFO", contact.info)
            contentValues.put("KEY_IMAGE", contact.image)

            dataBase.update("ContactPlus", contentValues, "KEY_ID =? ", arrayOf(contact.id.toString()))
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ()
    }

    override fun deleteContactFromDataBase(contact: Contact) {
        disposable = Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<MutableList<Contact>> ->
            val id: Double = contact.id

            dataBase.delete("ContactPlus", "KEY_ID = " + id, null)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ()
    }

    override fun addContactToDataBase(contact: Contact) {
        disposable = Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<MutableList<Contact>> ->
            val contentValues: ContentValues = ContentValues()
            contentValues.put("KEY_ID", contact.id)
            contentValues.put("KEY_NAME", contact.name)
            contentValues.put("KEY_INFO", contact.info)
            contentValues.put("KEY_IMAGE", contact.image)

            dataBase.insert("ContactPlus", null, contentValues)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ()    }

    override fun closeThreads() {
        disposable.dispose()
    }

}