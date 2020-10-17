package com.yandex.smur.marina.myfinalproject.shoppinglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.myfinalproject.R
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import kotlinx.android.synthetic.main.item_shopping_list.*


class ShoppingListFragment : Fragment() {

    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shoppingList : MutableList<String> = emptyList<String>() as MutableList<String>

        recyclerViewShoppingList.apply {
            adapter = ShoppingListAdapter (shoppingList)
            viewManager = LinearLayoutManager(activity)
        }

        buttonRemoveAllInShopingList.setOnClickListener {
            //delete all item from shopping list
        }

        buttonDeleteItemInShoppingList.setOnClickListener {
            // delete item from shopping list
        }
    }
}