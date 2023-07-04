package com.example.trevo.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.trevo.R
import com.example.trevo.model.Product
import com.example.trevo.view.recyclerview.ListProductAdapter

class OrderActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.adapter = ListProductAdapter(this, products = listOf(Product("vasco", "http://10.0.0.43:8080/trevo/api/produto/foto/product_tour_15_1595611598493_ADVANCE_2000_VORTEX_16.jpg")))
        val button = findViewById<Button>(R.id.buttonFooterOrder)

    }

    fun OrderToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}