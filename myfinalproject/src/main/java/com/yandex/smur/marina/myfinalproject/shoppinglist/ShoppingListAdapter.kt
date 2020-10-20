package com.yandex.smur.marina.myfinalproject.shoppinglist

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.recipe_activity.Ingredient
import com.yandex.smur.marina.myfinalproject.sqlite_database.DBHelperSelectedIngredients
import kotlinx.android.synthetic.main.item_shopping_list.view.*

class ShoppingListAdapter (val shoppingList : MutableList<Ingredient>?,
                            private val listener: ShoppingListAdapter.OnclickListenerAdapter)
    : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListItemViewHolder> (){


    public interface OnclickListenerAdapter {
        fun onItemClick(ingredient: Ingredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemViewHolder =
            ShoppingListItemViewHolder(
                    itemView = parent.run { LayoutInflater.from(context)
                            .inflate(R.layout.item_shopping_list, this, false) }
            )


    override fun onBindViewHolder(holder: ShoppingListItemViewHolder, position: Int) {
       holder.bind(shoppingList!![position], listener)
    }

    override fun getItemCount(): Int {
       return shoppingList?.size ?:0
    }

    public fun deleteIngredientR(ingredient: Ingredient){
        val idIngredient = ingredient.id
          for (i: Int in shoppingList!!.indices) {
              if (shoppingList[i].id.equals(idIngredient)){
                  shoppingList.removeAt(i)
                  break
              }
          }
        notifyDataSetChanged()
    }

    public fun deleteAll() {
        shoppingList!!.clear()
        notifyDataSetChanged()
    }

    class ShoppingListItemViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView) {

        public fun bind (ingredient: Ingredient, listener: ShoppingListAdapter.OnclickListenerAdapter) {
            with(ingredient) {
                itemView.apply {
                    itemShoppingList.text = ingredient.ingredient

                    itemView.buttonDeleteItemInShoppingList.setOnClickListener{
                            listener.onItemClick(ingredient)
                        }
                    }
                }
            }
        }
    }
