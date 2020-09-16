package com.yandex.smur.marina.task5

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class ContactListAdapter(private var contacts: MutableList<Contact> = mutableListOf(), private val listener: OnclickListener?) :
        RecyclerView.Adapter<ContactListAdapter.ContactItemViewHolder>() {

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

    internal fun setContacts(contacts: MutableList<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
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


    public fun filterList(filterList: MutableList<Contact>) {
        contacts = filterList
        notifyDataSetChanged()
    }

    class ContactItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
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

            itemView.setOnClickListener {
                listener?.onItemClick(contact)
            }
        }
    }
}