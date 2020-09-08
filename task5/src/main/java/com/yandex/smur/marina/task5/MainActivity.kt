package com.yandex.smur.marina.task5

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var persons: RecyclerView
    private lateinit var addContact: ImageButton
    private lateinit var searchView : EditText
    private lateinit var emptyView : TextView
    private lateinit var viewAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var adapter : ContactListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ContactListAdapter()
        persons = findViewById<RecyclerView>(R.id.persons).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        addContact = findViewById(R.id.addPerson)
        addContact.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, com.yandex.smur.marina.task5.AddPersonActivity::class.java)
            startActivityForResult(intent, 2)
        })

        emptyView = findViewById(R.id.emptyView)
        searchView = findViewById(R.id.search_edit_frame)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2){
            if (resultCode == RESULT_OK) {
                val contact = data!!.extras!!
                        .getSerializable(com.yandex.smur.marina.task5.Contact::class.java.simpleName)
                        as com.yandex.smur.marina.task5.Contact?
                updateList(contact)
            }
        }
    }

    private fun updateList(contact: Contact?) : Unit {
        adapter = persons.adapter as ContactListAdapter
        if (contact != null) {
            adapter?.addItem(contact)
        }
    }

//    private fun updateList(contact: com.yandex.smur.marina.hw3.Contact) {
//        adapter = persons.adapter as MainActivity.ContactListAdapter?
//        if (adapter != null) {
//            adapter.addItem(contact)
//        }
//    }

    private class ContactListAdapter ():
            RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder>() {


        private val contacts : MutableList<Contact> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
            var inflater = LayoutInflater.from(parent.context)
            return ContactItemViewHolder(inflater, parent)
        }

        override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
            holder.bind(contacts[position])
        }

        override fun getItemCount(): Int {
            return contacts?.size ?: 0
        }

        public fun addItem(contact: Contact) : Unit {
            contacts.add(contact)
            notifyItemChanged(contacts.indexOf(contact))
        }

        private class ContactItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
                RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)) {
            private var itemName : TextView? = null
            private var itemInfo : TextView? = null
            private var imageView : ImageView? = null

            init {
                itemName = itemView.findViewById(R.id.item_name)
                itemInfo = itemView.findViewById(R.id.item_info)
                imageView = itemView.findViewById(R.id.image_item)
            }

            public fun bind(contact: Contact) {
                itemName?.text = contact.name
                itemInfo?.text = contact.info
                imageView?.setImageResource(contact.image)

            }

        }

    }
}
