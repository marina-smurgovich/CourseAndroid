package com.yandex.smur.marina.myfinalproject.search_result

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.yandex.smur.marina.myfinalproject.R
import com.yandex.smur.marina.myfinalproject.SelectedFragment
import com.yandex.smur.marina.myfinalproject.api.Ingredient
import com.yandex.smur.marina.myfinalproject.api.RecipeDataModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_with_recipe.*
import kotlinx.android.synthetic.main.item_for_list_for_search_result.view.*

public const val REQUEST_CODE_ACTIVITYWITHRECIPE = "14568"

class ActivityWithRecipe : AppCompatActivity() {

    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_recipe)

        settingActivity()

        val recipe: RecipeDataModel = intent.getSerializableExtra("recipe") as RecipeDataModel

        Glide.with(this)
                .load(recipe.urlImage)
                .into(imageViewOnActivityWithRecipe)

        titleOnActivityWithRecipe.text = recipe.title

        textViewNumberOfServingOnActivityWithRecipe.text = recipe.numberOfServings.toString()

        textViewEnergyOnActivityWithRecipe.text = recipe.energy.toString()

//        textViewProteinOnActivityWithRecipe.text = recipe.protein.toString()

//        textViewFatOnActivityWithRecipe.text = recipe.fat.toString()

//        textViewCarbsOnActivityWithRecipe.text = recipe.carbs.toString()

        // ingredients
//        val list = recipe.listOfIngredients
//        initIngredients(list)

        seeTheFullRecipe(recipe.urlRecipe)

        addToFavourites(recipe)

        back()

    }

    private fun settingActivity() {
        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
        val navController = host.navController

        //включила боковое меню
        val sideBar = findViewById<NavigationView>(R.id.nav_view)
        sideBar?.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout = drawer_layout)
        val tooBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        tooBar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun seeTheFullRecipe(url: String) {
        buttonSeeTheFullRecipe.setOnClickListener {
            val intent = Intent(this@ActivityWithRecipe, ActivityWebPageWithRecipe::class.java)
            intent.putExtra("seeTheFullRecipe", url)
            startActivity(intent)
        }
    }

    private fun addToFavourites(recipe: RecipeDataModel) {
        buttonAddToFavourites.setOnClickListener {
//            val f = SelectedFragment()
//            val args = Bundle()
//            args.putSerializable("addToFavourites", recipe)
//            f.arguments = args

            // DataBase
        }
    }

    private fun back() {
        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun initIngredients (listOfIngredients: List<Ingredient>) {
        listWithIngredients.apply {
            adapter = IngredientsAdapter(listOfIngredients)
        }
        listWithIngredients.layoutManager = GridLayoutManager(this@ActivityWithRecipe, 2)

    }
}