package com.yandex.smur.marina.myfinalproject.selected

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import com.yandex.smur.marina.myfinalproject.search_result.ListSearchResultAdapter
import com.yandex.smur.marina.myfinalproject.shoppinglist.ShoppingListAdapter
import kotlinx.android.synthetic.main.item_for_list_for_search_result.view.*
import kotlinx.android.synthetic.main.item_for_list_for_search_result.view.imageViewFromItemForListForSearchResult
import kotlinx.android.synthetic.main.item_selected.view.*
import kotlinx.android.synthetic.main.item_shopping_list.view.*

class SelectedAdapter (val listOfSelectedRecipes : MutableList<RecipeDataModel>?,
                       private val listener : ListSearchResultAdapter.OnclickListenerAdapter?)
    : RecyclerView.Adapter<SelectedAdapter.SelectedListItemViewHolder> (){

    public interface OnclickListenerAdapter {
        fun onItemClick(recipe: RecipeDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):SelectedListItemViewHolder =
            SelectedListItemViewHolder (
                    itemView = parent.run { LayoutInflater.from(context)
                            .inflate(R.layout.item_selected, this, false) }
            )


    override fun onBindViewHolder(holder: SelectedListItemViewHolder, position: Int) {
        if (listener != null) {
        holder.bind(listOfSelectedRecipes!![position], listener)
        }
    }

    override fun getItemCount(): Int {
        return listOfSelectedRecipes?.size ?:0
    }

    class SelectedListItemViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView) {
        public fun bind (recipe : RecipeDataModel, listener: ListSearchResultAdapter.OnclickListenerAdapter) {
            with(recipe) {
                itemView.apply {
                    Glide.with(itemView.context)
                            .load(urlImage)
                            .into(itemView.imageViewSelected)

                    itemSelected.text = title
                    itemView.setOnClickListener{
                        listener.onItemClick(recipe)
                }
            }
        }
    }
}