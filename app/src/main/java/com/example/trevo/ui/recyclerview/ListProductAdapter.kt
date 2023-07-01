package com.example.trevo.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trevo.MainFragment
import com.example.trevo.R
import com.example.trevo.model.Product

class ListProductAdapter(private val context: Context, private val products: List<Product>)
    : RecyclerView.Adapter<ListProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(product: Product) {
            val title = itemView.findViewById<TextView>(R.id.cardTitle);
            val img = itemView.findViewById<ImageView>(R.id.cardImage)
            title.text = product.nome;

            Glide.with(itemView.context)
                .load(product.imagem)
                .into(img)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder{
        val inflater = LayoutInflater.from(context)
        val view =inflater.inflate(R.layout.product_item, parent, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.vincula(product)
    }

}
