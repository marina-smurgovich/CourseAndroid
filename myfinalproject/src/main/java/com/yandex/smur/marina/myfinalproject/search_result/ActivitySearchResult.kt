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
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_search_result.*
import okhttp3.OkHttpClient

class ActivitySearchResult : AppCompatActivity() {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private var disposable : Disposable? = null
    var searchObject : SearchObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val arguments : Bundle = intent.extras!!
        searchObject = arguments.get("mySearchObject") as SearchObject

        Log.d("ActivitySearchResult", searchObject!!.searchingByKeyword)

        settingActivity ()

        //настраиваю ресайклвью
        listWithResultOfSearch.apply {
            adapter = ListSearchResultAdapter(
                    object : ListSearchResultAdapter.OnclickListenerAdapter{
                override fun onItemClick(recipe: RecipeDataModel
                )
                {
                    val intent = Intent(this@ActivitySearchResult, ActivityWithRecipe::class.java)
                    intent.putExtra("recipe", recipe)
                    startActivity(intent)
                }
            })
            viewManager = LinearLayoutManager(this@ActivitySearchResult)
        }
        fetchNewsList(searchObject!!)

    }

    override fun onResume() {
        super.onResume()
    }

    private fun fetchNewsList(
            searchObject: SearchObject
    ) {
        disposable = RecipesRepositoryImpl(
                okHttpClient = OkHttpClient(),
                recipesDataModelMapper = RecipesDataModelMapper()
        ).getRecipes(searchObject) // позже буду сюда закидывать полностью объект для поиска
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {list ->
                            Log.d("ActivitySearchResult", list.toString())
                            (listWithResultOfSearch.adapter as? ListSearchResultAdapter)?.updateItemList(list)
                        },
                        {throwable -> Log.d("ActivitySearchResult", throwable.toString())}
                )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun settingActivity () {
        val host : NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
        val navController = host.navController

        //включила боковое меню
        val sideBar = findViewById<NavigationView>(R.id.nav_view)
        sideBar?.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout = drawer_layout)
        val tooBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        tooBar.setupWithNavController(navController, appBarConfiguration)
    }
}