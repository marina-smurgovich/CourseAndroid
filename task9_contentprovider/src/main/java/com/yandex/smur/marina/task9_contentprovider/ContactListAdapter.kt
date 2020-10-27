package com.yandex.smur.marina.task9_contentprovider

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


public class ContactListAdapter(private var contacts: MutableList<Contact> = mutableListOf()) :
        RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder>() {

    private var contactsInt: MutableList<Contact> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return ContactItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
            holder.bind(contacts.get(position))
    }

    internal fun setContacts(contacts: MutableList<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    public fun updateList (contact : Contact) {
        if(!contacts.contains(contact) && !contactsInt.contains(contact)) {
            contacts.add(contact)
            contactsInt.add(contact)
        }
        notifyDataSetChanged()
    }


    public fun filterList(filterList: MutableList<Contact>) {
        contacts = filterList
        notifyDataSetChanged()
    }

    class ContactItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)) {
        private var itemName: TextView = itemView.findViewById(R.id.item_name)
        private var itemInfo: TextView = itemView.findViewById(R.id.item_info)
        private  var imageView: ImageView = itemView.findViewById(R.id.image_item)

        public fun bind(contact: Contact) {
            itemName.text = contact.name
            itemInfo.text = contact.info
            imageView.setImageResource(contact.image)

        }
    }
}