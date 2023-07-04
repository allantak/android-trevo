package com.example.trevo.ui.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.example.trevo.MainFragment
import com.example.trevo.R
import com.example.trevo.model.Product
import com.example.trevo.ui.types.OnItemClickListener

class ListProductAdapter(private val context: Context, private val products: List<Product>)
    : RecyclerView.Adapter<ListProductAdapter.ViewHolder>() {


    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }
        fun vincula(product: Product) {
            val title = itemView.findViewById<TextView>(R.id.cardTitle);
            val img = itemView.findViewById<ImageView>(R.id.cardImage)
            title.text = product.nome;

            try {
                Glide.with(itemView.context)
                    .load(product.imagem)
                    .into(img)
            }catch ( e: GlideException){
                Log.d("TAG", e.toString())
            }
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
        Log.d("TAG", products[position].toString())
        holder.vincula(product)
    }

}
