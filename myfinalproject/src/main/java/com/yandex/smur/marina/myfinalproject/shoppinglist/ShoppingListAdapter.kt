package com.yandex.smur.marina.myfinalproject.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.myfinalproject.R
import kotlinx.android.synthetic.main.item_shopping_list.view.*

class ShoppingListAdapter (val shoppingList : MutableList<String>?)
    : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListItemViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemViewHolder =
            ShoppingListItemViewHolder(
                    itemView = parent.run { LayoutInflater.from(context)
                            .inflate(R.layout.item_shopping_list, this, false) }
            )


    override fun onBindViewHolder(holder: ShoppingListItemViewHolder, position: Int) {
       holder.bind(shoppingList!![position])
    }

    override fun getItemCount(): Int {
       return shoppingList?.size ?:0
    }


    class ShoppingListItemViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView) {
        public fun bind (itemInShoppingList : String) {
            with(itemInShoppingList) {
                itemView.apply {
                    itemShoppingList.text = itemInShoppingList.toString()
                }
            }
        }
    }
}