package com.example.trevo.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.example.trevo.R
import com.example.trevo.model.Product

class DetailActivity : AppCompatActivity() {
    private var produtoId: String? = null;
    private var produtoNome: String? = null;
    private var produtoImg: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        produtoNome = intent.getStringExtra("produto_nome")
        produtoImg = intent.getStringExtra("produto_img")
        produtoId = intent.getStringExtra("produto_id")
        val produtoCapa = intent.getStringExtra("produto_capa")
        val productDesc = intent.getStringExtra("produto_descricao")
        val productArea = intent.getStringExtra("produto_area")
        val productCulture = intent.getStringArrayExtra("produto_culturas")
        var joinString = productCulture?.joinToString(separator = ", ")

        var title = findViewById<TextView>(R.id.titleDetail)
        var description = findViewById<TextView>(R.id.descriptionDetail)
        var area = findViewById<TextView>(R.id.infoArea)
        var culture = findViewById<TextView>(R.id.infoCulture)
        val img = findViewById<ImageView>(R.id.imageProduct)
        title.text = produtoNome
        description.text = productDesc
        area.text = productArea
        culture.text = joinString

        try {
            Glide.with(this)
                .load("http://10.0.0.43:8080/trevo/api/produto/foto/"+produtoCapa)
                .into(img)
        }catch ( e: GlideException){
            Log.d("TAG", e.toString())
        }


    }

    fun DetailToMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun MakeOrder(view : View){
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("produto_id", produtoId)
        intent.putExtra("produto_nome", produtoNome)
        intent.putExtra("produto_img", produtoImg)
        startActivity(intent)

    }
}