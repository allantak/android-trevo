package com.example.trevo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.trevo.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        findViewById<ImageView>(R.id.imageMenu).setOnClickListener {
            drawerLayout.openDrawer(
                GravityCompat.START
            )
        }

        var navigationView:NavigationView = findViewById(R.id.navigationView);
        navigationView.itemIconTintList = null;

        var navController:NavController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController)
    }
}