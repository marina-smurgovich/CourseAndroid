package com.yandex.smur.marina.myfinalproject.search_result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import kotlinx.android.synthetic.main.item_for_list_for_search_result.view.*


class ListSearchResultAdapter (
        private val listener : OnclickListenerAdapter?
) : RecyclerView.Adapter<ListSearchResultAdapter.RecipeItemViewHolder>() {

    private var listWithResultSearch = mutableListOf<RecipeDataModel>()

    public interface OnclickListenerAdapter {
        fun onItemClick(recipe: RecipeDataModel)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RecipeItemViewHolder (
                    itemView = parent.run {LayoutInflater.from(context).inflate(R.layout.item_for_list_for_search_result, this, false)}
            )

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        if (listener != null) {
            holder.bind(listWithResultSearch[position]
                    , listener
            )
        }
    }

    override fun getItemCount()=  listWithResultSearch.size


    fun updateItemList(listWithResultSearchIn : List<RecipeDataModel>) {
        listWithResultSearch.apply {
            clear()
            addAll(listWithResultSearchIn)
        }
        notifyDataSetChanged()
    }

    class RecipeItemViewHolder(itemView : View) :
            RecyclerView.ViewHolder(itemView){

        public fun bind (recipe : RecipeDataModel
                         , listener: OnclickListenerAdapter
        ) {
            with(recipe){
                itemView.apply {
                    textViewFromItemForListForSearchResult.text = title
                    itemView.setOnClickListener{
                    listener.onItemClick(recipe)
                }
                }

                Glide.with(itemView.context)
                        .load(urlImage)
                        .into(itemView.imageViewFromItemForListForSearchResult)

//                itemView.setOnClickListener{
//                    listener?.onItemClick(recipe)
//                }
            }
        }
    }
}

