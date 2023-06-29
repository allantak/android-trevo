package com.example.trevo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        findViewById<ImageView>(R.id.imageMenu).setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        })

        var navigationView:NavigationView = findViewById(R.id.navigationView);
        navigationView.itemIconTintList = null;

        var navController:NavController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController)
    }
}