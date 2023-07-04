package com.example.trevo.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.trevo.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val produtoNome = intent.getStringExtra("produto_nome")
        val produtoImg = intent.getStringExtra("produto_img")
        println(produtoNome);
        println(produtoImg);
    }

    fun DetailToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}