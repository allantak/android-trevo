package com.example.trevo.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trevo.R
import com.example.trevo.model.Product
import com.example.trevo.service.MainRetrofit
import com.example.trevo.service.model.Cliente
import com.example.trevo.service.model.Pedido
import com.example.trevo.view.dialog.OrderDialog
import com.example.trevo.view.recyclerview.ListProductAdapter
import com.example.trevo.view.types.OnItemClickListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : AppCompatActivity(), OnItemClickListener, OrderDialog.OrderDialogListener {
    var productList = mutableListOf<Product>()

    @SuppressLint("MissingInflatedId")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val button = findViewById<Button>(R.id.buttonFooterOrder)

        button.setOnClickListener { openDialog() }

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
        var adapter = ListProductAdapter(this, products = productList, "cardOrder")
        adapter.setOnItemClickListener(this@OrderActivity)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        productList.remove(productList[position])
        displayProductList()
        saveProductList()
    }

    private fun openDialog() {
        val orderDialog = OrderDialog()
        orderDialog.show(supportFragmentManager, "example dialog")
    }

    override fun applyTexts(name: String?, email: String?, phone: String?) {
        onSubmit(name,email, phone )
    }

    private fun onSubmit(name: String?, email: String?, phone: String?){
        val pedido = Pedido(
            cliente = Cliente(name, email, phone),
            produtos = listOf(productList.size)
        )

        MainRetrofit().productService.propose(pedido).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("Deu certo")
                } else {
                    // Requisição retornou um código de erro
                    val errorCode: Int = response.code()
                    val errorMessage: String = response.message()
                    println(errorCode)
                    println(errorMessage)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println(t)
            }
        })

    }

}