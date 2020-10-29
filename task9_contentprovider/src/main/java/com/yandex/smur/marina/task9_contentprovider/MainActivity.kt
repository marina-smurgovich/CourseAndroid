package com.yandex.smur.marina.task9_contentprovider

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var contacts: MutableList<Contact> = mutableListOf()
    private lateinit var adapter: ContactListAdapter
    private lateinit var contact : Contact
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()
        emptyList()
        searchListener()
        buttonAddPerson.setOnClickListener{getAllContactFromTask5()}
    }

    private fun getAllContactFromTask5() {
        val cursor = contentResolver.query(Uri.parse(URI_PATH), null, null, null, null)
        if (cursor != null) {
            val contactId = cursor.getColumnIndex("KEY_ID")
            val contactName = cursor.getColumnIndex("KEY_NAME")
            val contactInfo = cursor.getColumnIndex("KEY_INFO")
            val contactImage = cursor.getColumnIndex("KEY_IMAGE")
            while (cursor.moveToNext()) {
                contact = Contact(
                        cursor.getDouble(contactId),
                        cursor.getString(contactName),
                        cursor.getString(contactInfo),
                        cursor.getInt(contactImage)
                )
                adapter.updateList(contact)
            }

            cursor.close()
        }

    }

    private fun setRecyclerView() {
        persons.apply {
            viewManager = LinearLayoutManager(this@MainActivity)
            viewAdapter = ContactListAdapter(contacts)
        }
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

    private fun emptyList() {
        if (viewAdapter.itemCount == 0) {
            persons.visibility = View.GONE
            textViewEmptyView.visibility = View.VISIBLE
        } else {
            persons.visibility = View.VISIBLE
            textViewEmptyView.visibility = View.GONE
        }
    }

    companion object {
        private const val URI_PATH = "content://com.yandex.smur.marina.task5/ContactPlus"
    }

}
