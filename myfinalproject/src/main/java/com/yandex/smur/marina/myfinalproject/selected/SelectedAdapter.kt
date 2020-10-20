package com.yandex.smur.marina.myfinalproject.selected

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import kotlinx.android.synthetic.main.item_selected.view.*

class SelectedAdapter(
        val listOfSelectedRecipes: MutableList<RecipeDataModel>?,
        private val listener: OnclickListenerAdapter?,
        private val listenerBtn: OnClickListenerButton?,
) : RecyclerView.Adapter<SelectedAdapter.SelectedListItemViewHolder>() {

    public interface OnclickListenerAdapter {
        fun onItemClick(recipe: RecipeDataModel)
    }

    public interface OnClickListenerButton {
        fun onItemClick(recipe: RecipeDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedListItemViewHolder =
            SelectedListItemViewHolder(
                    itemView = parent.run {
                        LayoutInflater.from(context)
                                .inflate(R.layout.item_selected, this, false)
                    }
            )


    override fun onBindViewHolder(holder: SelectedListItemViewHolder, position: Int) {
        if (listener != null) {
            holder.bind(listOfSelectedRecipes!![position], listener, listenerBtn)
        }
    }

    override fun getItemCount(): Int {
        return listOfSelectedRecipes?.size ?: 0
    }

    public fun deleteRecipe(recipe: RecipeDataModel) {
        val idRecipe = recipe.id
        for (i: Int in listOfSelectedRecipes!!.indices) {
            if (listOfSelectedRecipes[i].id.equals(idRecipe)) {
                listOfSelectedRecipes.removeAt(i)
                break
            }
        }
        notifyDataSetChanged()
    }

    public fun deleteallRecipe() {
        if (listOfSelectedRecipes != null) {
            listOfSelectedRecipes!!.clear()
        }
        notifyDataSetChanged()
    }

    class SelectedListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public fun bind(
                recipe: RecipeDataModel, listener: OnclickListenerAdapter?,
                listenerBtn: OnClickListenerButton?,
        ) {
            with(recipe) {
                itemView.apply {
                    Glide.with(itemView.context)
                            .load(urlImage)
                            .into(itemView.imageViewSelected)

                    itemSelected.text = title

                    itemView.buttonDeleteItemInSelected.setOnClickListener {
                        listenerBtn!!.onItemClick(recipe)
                    }

                    itemView.setOnClickListener {
                        listener!!.onItemClick(recipe)
                    }
                }
            }
        }
    }
}