package com.yandex.smur.marina.task6_async

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var persons: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var adapter: ContactListAdapter
    private lateinit var dataBase: SQLiteDatabase
    private lateinit var contentValues: ContentValues
    private lateinit var dbHelper: DBHelper
    private var contacts: MutableList<Contact> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this);
        dataBase = dbHelper.writableDatabase;
        contentValues = ContentValues();

        contacts = addContactsFromDataBase()

        persons = findViewById<RecyclerView>(R.id.persons).apply {
            viewManager = LinearLayoutManager(this@MainActivity)
            viewAdapter = ContactListAdapter(contacts, object : ContactListAdapter.OnclickListener {
                override fun onItemClick(contact: Contact) {
                    val intent = Intent(this@MainActivity, EditContactActivity::class.java)
                    intent.putExtra("contact", contact)
                    startActivityForResult(intent, 3)
                }
            })
            adapter = viewAdapter
        }

        emptyList()


        buttonAddPerson.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, com.yandex.smur.marina.task6_async.AddPersonActivity::class.java)
            startActivityForResult(intent, 2)
        })


        searchListener()
    }

    private fun searchListener() {
        editViewSearchEditFrame.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                adapter = (persons.adapter as ContactListAdapter?)!!
                if (adapter != null) {
                    filter(editable.toString())
                }
            }
        })
    }

    public fun filter(text: String) {
        val filterList: MutableList<Contact> = mutableListOf()
        for (item in contacts) {
            if (item.name.contains(text.toString())) {
                filterList.add(item)
            }
        }
        adapter.filterList(filterList)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName) as Contact
                updateList(contact)
                addContactToDataBase(contact)
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_CANCELED) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName) as Contact
                updateListAfterRemove(contact)
                deleteContactFromDataBase(contact)
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName) as Contact
                updateListBeforeChange(contact)
                editCountFromDataBase(contact)
            }
        }
        emptyList()
    }

    //Work with recyclerView (update)
    private fun updateList(contact: Serializable?) {
        adapter = (persons.adapter as ContactListAdapter?)!!
        if (contact != null) {
            adapter.addItem(contact as Contact)
        }
    }

    private fun updateListAfterRemove(contact: Serializable?) {
        adapter = (persons.adapter as ContactListAdapter?)!!
        if (contact != null) {
            adapter.deleteItem(contact as Contact)
        }
        adapter.notifyDataSetChanged()
    }

    private fun updateListBeforeChange(contact: Serializable?) {
        adapter = (persons.adapter as ContactListAdapter?)!!
        if (contact != null) {
            adapter.replaceItem(contact as Contact)
        }
        adapter.notifyDataSetChanged()
    }

    // RecyclerView or emptyView
    private fun emptyList() {
        if (viewAdapter.itemCount == 0) {
            persons.visibility = View.GONE
            textViewEmptyView.visibility = View.VISIBLE
        } else {
            persons.visibility = View.VISIBLE
            textViewEmptyView.visibility = View.GONE
        }
    }


    //Work with dataBase
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
}
