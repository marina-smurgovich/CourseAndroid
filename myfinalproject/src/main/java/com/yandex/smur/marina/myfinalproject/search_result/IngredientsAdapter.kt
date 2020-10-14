package com.yandex.smur.marina.myfinalproject.search_result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.Ingredient
import kotlinx.android.synthetic.main.item_for_recyclerview_activity_with_recipe.view.*

class IngredientsAdapter (val listOfIngredients : List<Ingredient>) :
        RecyclerView.Adapter<IngredientsAdapter.IngredientItemViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            IngredientItemViewHolder (
                    itemView = parent.run { LayoutInflater.from(context).inflate(R.layout.item_for_recyclerview_activity_with_recipe, this, false) }
            )

    override fun onBindViewHolder(holder: IngredientItemViewHolder, position: Int) {
        holder.bind(listOfIngredients[position])
    }

    override fun getItemCount(): Int {
        return listOfIngredients?.size ?: 0
    }

    class IngredientItemViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        public fun bind (ingredient: Ingredient) {
            with(ingredient) {
                itemView.apply {
                    textViewOfIngredient.text = ingredient.ingredient
                }
            }
        }
    }

}