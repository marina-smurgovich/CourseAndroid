package com.yandex.smur.marina.task5

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var persons: RecyclerView? = null
    private var addContact: ImageButton? = null
    private var searchView : EditText? = null
    private var emptyView : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.search_edit_frame)
        persons = findViewById(R.id.persons)
        addContact = findViewById(R.id.addPerson)
        emptyView = findViewById(R.id.emptyView)
    }

    private class ContactListAdapter () : RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        class ContactItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
            var itemName : TextView? = null
            var itemInfo : TextView? = null
            var imageView : ImageView? = null

            constructor(itemView: View, parent: ContactListAdapter) : this(itemView) {
                ContactListAdapter.

            }
        }

    }
}
