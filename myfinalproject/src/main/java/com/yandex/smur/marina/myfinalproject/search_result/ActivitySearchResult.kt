package com.yandex.smur.marina.myfinalproject.search_result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.Recipe

class ActivitySearchResult : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var adapter: ListSearchResultAdapter
    private var listSearchResultAdapter: MutableList<Recipe> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        recyclerView = findViewById<RecyclerView>(R.id.listWithResultOfSearch).apply {
            viewManager = LinearLayoutManager(this@ActivitySearchResult)
            viewAdapter = ListSearchResultAdapter(listSearchResultAdapter, object : ListSearchResultAdapter.OnclickListenerAdapter{
                override fun onItemClick(recipe: Recipe) {
                    val intent = Intent(this@ActivitySearchResult, ActivityWithRecipe::class.java)
                    intent.putExtra("recipe", recipe)
                    startActivity(intent)
                }
            })
            adapter = viewAdapter
        }
    }
}