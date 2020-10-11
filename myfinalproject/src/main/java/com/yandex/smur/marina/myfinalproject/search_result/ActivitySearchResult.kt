package com.yandex.smur.marina.myfinalproject.search_result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.RecipeDataModel
import com.yandex.smur.marina.myfinalproject.api.RecipesDataModelMapper
import com.yandex.smur.marina.myfinalproject.api.RecipesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient

class ActivitySearchResult : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var adapter: ListSearchResultAdapter
    private var listSearchResultAdapter: MutableList<RecipeDataModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        ////
        val arguments : Bundle? = intent.extras
        val searchObject : SearchObject = arguments!!.get("mySearchObject") as SearchObject
        ////

        RecipesRepositoryImpl(
                okHttpClient = OkHttpClient(),
                recipesDataModelMapper = RecipesDataModelMapper()
        ).getRecipes(searchObject.searchingByKeyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {list -> Log.d("ActivitySearchResult", list.toString())},
                        {throwable -> Log.d("ActivitySearchResult", throwable.toString())}
                )

        recyclerView = findViewById<RecyclerView>(R.id.listWithResultOfSearch).apply {
            viewManager = LinearLayoutManager(this@ActivitySearchResult)
            viewAdapter = ListSearchResultAdapter(listSearchResultAdapter, object : ListSearchResultAdapter.OnclickListenerAdapter{
                override fun onItemClick(recipe: RecipeDataModel) {
                    val intent = Intent(this@ActivitySearchResult, ActivityWithRecipe::class.java)
                    intent.putExtra("recipe", recipe)
                    startActivity(intent)
                }
            })
            adapter = viewAdapter
        }
    }

}