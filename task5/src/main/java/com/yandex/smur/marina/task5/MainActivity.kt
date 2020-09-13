package com.yandex.smur.marina.task5

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var persons: RecyclerView
    private lateinit var addContact: ImageButton
    private lateinit var searchView: EditText
    private lateinit var emptyView: TextView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var adapter: ContactListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ContactListAdapter(object : ContactListAdapter.OnclickListener {
            override fun onItemClick(contact: Contact) {
                val intent = Intent(this@MainActivity, com.yandex.smur.marina.task5.EditContactActivity::class.java)
                intent.putExtra("contact", contact)
                startActivityForResult(intent, 3)
            }

        })
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
        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                adapter = (persons.adapter as com.yandex.smur.marina.task5.MainActivity.ContactListAdapter?)!!
                if (adapter != null) {
                    adapter.filter(editable.toString())
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName)
                updateList(contact)
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_CANCELED) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName)
                updateListAfterRemove(contact)
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                var contact = data!!.getSerializableExtra(Contact::class.java.simpleName)
                updateListBeforeChange(contact)
            }
        }
        emptyList()
    }


    private fun updateList(contact: Serializable?) {
        adapter = (persons.adapter as com.yandex.smur.marina.task5.MainActivity.ContactListAdapter?)!!
        if (contact != null) {
            adapter.addItem(contact as Contact)
        }
    }

    private fun updateListAfterRemove(contact: Serializable?) {
        adapter = (persons.adapter as com.yandex.smur.marina.task5.MainActivity.ContactListAdapter?)!!
        if (contact != null) {
            adapter.deleteItem(contact as Contact)
        }
    }

    private fun updateListBeforeChange(contact: Serializable?) {
        adapter = (persons.adapter as com.yandex.smur.marina.task5.MainActivity.ContactListAdapter?)!!
        if (contact != null) {
            adapter.replaceItem(contact as Contact)
        }
        adapter.notifyDataSetChanged()
    }

    private fun emptyList() {
        adapter = (persons.adapter as com.yandex.smur.marina.task5.MainActivity.ContactListAdapter?)!!
        if (adapter.itemCount == 0) {
            persons.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            persons.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }

    private class ContactListAdapter(private val listener: OnclickListener?) :
            RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder>() {


        private var contacts: MutableList<Contact> = mutableListOf()
        var count: Int = 0

        public interface OnclickListener {
            fun onItemClick(contact: Contact)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
            var inflater = LayoutInflater.from(parent.context)
            return ContactItemViewHolder(inflater, parent)
        }

        override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
            if (listener != null) {
                holder.bind(contacts.get(position), listener)
            }
        }

        override fun getItemCount(): Int {
            return contacts?.size ?: 0
        }

        public fun addItem(contact: Contact): Unit {
            contacts.add(contact as Contact)
            notifyItemChanged(contacts.indexOf(contact))
        }

        public fun deleteItem(contact: Contact) {
            val idContact: Double = contact.id
            for (i in contacts.indices) {
                if (contacts[i].id.equals(idContact)) {
                    contacts.removeAt(i)
                    notifyItemRemoved(i)
                    break
                }
            }
        }

        public fun replaceItem(contact: Contact) {
            val idContact: Double = contact.id
            for (i in contacts.indices) {
                if (contacts[i].id.equals(idContact)) {
                    contacts[i] = contact
                }
            }
        }

        public fun filter(text: String) {
            val filterList: MutableList<Contact> = mutableListOf()
            for (item in contacts) {
                if (item.name.toLowerCase().contains(text.toLowerCase())) {
                    filterList.add(item)
                }
            }
            filterList(filterList)
        }

        private fun filterList (filterList: MutableList<Contact>) {
            contacts = filterList
            notifyDataSetChanged()
        }

        private class ContactItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
                RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)) {
            private var itemName: TextView? = null
            private var itemInfo: TextView? = null
            private var imageView: ImageView? = null

            init {
                itemName = itemView.findViewById(R.id.item_name)
                itemInfo = itemView.findViewById(R.id.item_info)
                imageView = itemView.findViewById(R.id.image_item)
            }

            public fun bind(contact: Contact, listener: OnclickListener) {
                itemName?.text = contact.name
                itemInfo?.text = contact.info
                imageView?.setImageResource(contact.image)

                itemView.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(p0: View?) {
                        listener?.onItemClick(contact)
                        adapterPosition
                    }
                })

            }

        }

    }
}
