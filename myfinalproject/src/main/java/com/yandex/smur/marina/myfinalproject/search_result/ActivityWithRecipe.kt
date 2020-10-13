package com.yandex.smur.marina.myfinalproject.search_result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.yandex.smur.marina.myfinalproject.R
import kotlinx.android.synthetic.main.activity_main.*

public const val REQUEST_CODE_ACTIVITYWITHRECIPE = "14568"

class ActivityWithRecipe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_recipe)

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