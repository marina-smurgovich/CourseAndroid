package com.yandex.smur.marina.myfinalproject.search_result

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import kotlinx.android.synthetic.main.item_for_list_for_search_result.view.*

class ListSearchResultAdapter (private var listWithResutSearch : MutableList<RecipeDataModel> = mutableListOf(),
                               private val listener : OnclickListenerAdapter?) : RecyclerView.Adapter<ListSearchResultAdapter.RecipeItemViewHolder>() {

    public interface OnclickListenerAdapter {
        fun onItemClick(recipe: RecipeDataModel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return RecipeItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        if (listener != null) {
            holder.bind(listWithResutSearch.get(position), listener)
        }
    }

    override fun getItemCount(): Int {
       return listWithResutSearch.size
    }

    class RecipeItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_for_list_for_search_result, parent, false)){
        private var title: TextView = itemView.findViewById(R.id.textViewFromItemForListForSearchResult)

        public fun bind (recipe : RecipeDataModel, listener: OnclickListenerAdapter) {
//            title.text = recipe.title

            Glide.with(itemView.context)
//                    .load(recipe.urlImage)
//                    .into(itemView.imageViewFromItemForListForSearchResult)

            itemView.setOnClickListener{
                listener?.onItemClick(recipe)
            }
        }
    }
}

