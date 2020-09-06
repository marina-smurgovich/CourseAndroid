package com.yandex.smur.marina.task5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.hw3.Contact
import java.util.*

class test {
    class ContactListAdapter(private val listener: OnclickListener) : RecyclerView.Adapter<ContactItemViewHolder>() {
        private var contacts: MutableList<Contact>? = ArrayList()
        var position = 0

        interface OnclickListener {
            fun onItemClick(contact: Contact?)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return ContactItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
            holder.bind(contacts!![position], listener)
        }

        override fun getItemCount(): Int {
            return if (contacts != null) contacts!!.size else 0
        }

        fun addItem(contact: Contact) {
            contacts!!.add(contact)
            notifyItemChanged(contacts!!.indexOf(contact))
        }

        fun deleteItem() {
            contacts!!.removeAt(position)
            notifyItemRemoved(position)
        }

        fun replaceItem(contact: Contact) {
            contacts!![position] = contact
        }

        fun filter(text: String) {
            val filterList = ArrayList<Contact>()
            for (item in contacts!!) {
                if (item.name.toLowerCase().contains(text.toLowerCase())) {
                    filterList.add(item)
                }
            }
            filterList(filterList)
        }

        private fun filterList(filterList: ArrayList<Contact>) {
            contacts = filterList
            notifyDataSetChanged()
        }

        inner class ContactItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val item_name: TextView
            private val item_phoneOrEmail: TextView
            private val imageView: ImageView
            fun bind(contact: Contact, listener: OnclickListener?) {
                item_name.text = contact.name
                item_phoneOrEmail.text = contact.telOrEmail
                imageView.setImageResource(contact.image)
                itemView.setOnClickListener {
                    if (listener != null) {
                        position = adapterPosition
                        listener.onItemClick(contact)
                    }
                }
            }

            init {
                item_name = itemView.findViewById(R.id.item_name)
                item_phoneOrEmail = itemView.findViewById(R.id.item_phoneOrEmail)
                imageView = itemView.findViewById(R.id.image_item)
            }
        }
    }
}
