package com.yandex.smur.marina.myfinalproject.search_result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import com.yandex.smur.marina.myfinalproject.api.RecipesDataModelMapper
import com.yandex.smur.marina.myfinalproject.api.RecipesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient

class ActivitySearchResult : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var adapter: ListSearchResultAdapter
    private var listSearchResultAdapter: MutableList<RecipeDataModel> = ArrayList()
    private var disposable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        ////
        val arguments : Bundle? = intent.extras
        val searchObject : SearchObject = arguments!!.get("mySearchObject") as SearchObject
        ////

        disposable = RecipesRepositoryImpl(
                okHttpClient = OkHttpClient(),
                recipesDataModelMapper = RecipesDataModelMapper()
        ).getRecipes(searchObject.searchingByKeyword) // позже буду сюда закидывать полностью объект для поиска
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {list -> Log.d("ActivitySearchResult", list.toString())},
                        {throwable -> Log.d("ActivitySearchResult", throwable.toString())}
                )

        val host : NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
        val navController = host.navController

        //включила боковое меню
        val sideBar = findViewById<NavigationView>(R.id.nav_view)
        sideBar?.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout = drawer_layout)
        val tooBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        tooBar.setupWithNavController(navController, appBarConfiguration)

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