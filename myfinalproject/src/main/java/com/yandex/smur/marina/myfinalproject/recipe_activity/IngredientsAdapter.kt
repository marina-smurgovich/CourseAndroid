package com.yandex.smur.marina.myfinalproject.recipe_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.myfinalproject.R
import kotlinx.android.synthetic.main.item_for_recyclerview_activity_with_recipe.view.*

class IngredientsAdapter (val listOfIngredients : MutableList<Ingredient>,
                          private val listener: IngredientsAdapter.OnclickListenerAdapter?) :
        RecyclerView.Adapter<IngredientsAdapter.IngredientItemViewHolder> (){

    public interface OnclickListenerAdapter {
        fun onItemClick(ingredient: Ingredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            IngredientItemViewHolder (
                    itemView = parent.run { LayoutInflater.from(context).inflate(R.layout.item_for_recyclerview_activity_with_recipe, this, false) }
            )

    override fun onBindViewHolder(holder: IngredientItemViewHolder, position: Int) {
        listener?.let { holder.bind(listOfIngredients[position], it) }
    }

    override fun getItemCount(): Int {
        return listOfIngredients.size ?: 0
    }

    class IngredientItemViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        public fun bind (ingredient: Ingredient, listener: IngredientsAdapter.OnclickListenerAdapter) {
            with(ingredient) {
                itemView.apply {
                    textViewOfIngredient.text = ingredient.ingredient

                    itemView.setOnClickListener {
                        listener.onItemClick(ingredient)
                    }
                }
            }
        }
    }

}