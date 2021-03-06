package com.github.ryu.andocchi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = supportFragmentManager.findFragmentById(R.id.activity_main_nav_host)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)

        // AppBarConfigurationの引数に渡したIDのFragmentは、toolbarに戻るボタンを表示させない
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_profile, R.id.nav_home, R.id.nav_skill, R.id.levelUpFragment, R.id.nav_memo, R.id.nav_code))

        NavigationUI.setupWithNavController(bottomNavigationView, navController!!.findNavController())
        setupActionBarWithNavController(navController.findNavController(), appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.activity_main_nav_host).navigateUp()
    }
}
