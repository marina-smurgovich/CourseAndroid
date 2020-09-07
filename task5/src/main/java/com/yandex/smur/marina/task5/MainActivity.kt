package com.yandex.smur.marina.task5

import android.os.Bundle
import android.view.LayoutInflater
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
        emptyView = findViewById(R.id.emptyView)
        searchView = findViewById(R.id.search_edit_frame)
    }

    private class ContactListAdapter (private val contacts: List<Contact>) :
            RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder>() {

        constructor() : this(emptyList())

//        private val contacts : MutableList<Contact> = mutableListOf()

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
