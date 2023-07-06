package com.example.trevo.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.trevo.R
import com.example.trevo.model.Product
import com.example.trevo.view.recyclerview.ListProductAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OrderActivity : AppCompatActivity() {
    var productList = mutableListOf<Product>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val button = findViewById<Button>(R.id.buttonFooterOrder)


        var produtoNome = intent.getStringExtra("produto_nome")
        var produtoImg = intent.getStringExtra("produto_img")


        val savedProductList = retrieveSavedProductList()
        if (savedProductList != null) {
            productList.addAll(savedProductList)
        }

        if (!produtoNome.equals(null) || !produtoImg.equals(null)) {
            val exampleProduct = Product(null, produtoNome, null, null, produtoImg, null, null)
            productList.add(exampleProduct)

        }

        displayProductList()
        saveProductList()

    }

    fun OrderToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun retrieveSavedProductList(): MutableList<Product>? {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("productList", null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<Product>>() {}.type
            Gson().fromJson(json, type)
        } else {
            null
        }
    }

    private fun saveProductList() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(productList)
        editor.putString("productList", json)
        editor.apply()
    }

    private fun displayProductList() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        val productList: List<Product> = productList.toList()
        recyclerView.adapter = ListProductAdapter(this, products = productList)
    }

}