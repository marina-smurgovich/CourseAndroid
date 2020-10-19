package com.yandex.smur.marina.myfinalproject.selected

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import kotlinx.android.synthetic.main.item_selected.view.*

class SelectedAdapter (val listOfSelectedRecipes : MutableList<RecipeDataModel>?,
                       private val listener : OnclickListenerAdapter?)
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

    fun updateItemList(itemListIn : List<RecipeDataModel>) {
        listOfSelectedRecipes?.apply {
            clear()
            addAll(itemListIn)
        }
        notifyDataSetChanged()
    }

    class SelectedListItemViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView) {
        public fun bind (recipe: RecipeDataModel, listener: OnclickListenerAdapter?) {
            with(recipe) {
                itemView.apply {
                    Glide.with(itemView.context)
                            .load(urlImage)
                            .into(itemView.imageViewSelected)

                    itemSelected.text = title
                    itemView.setOnClickListener{
                        listener!!.onItemClick(recipe)
                }
            }
        }
    }
}}