package com.example.trevo.view.recyclerview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.example.trevo.R
import com.example.trevo.model.Product
import com.example.trevo.view.activity.DetailActivity
import com.example.trevo.view.activity.OrderActivity
import com.example.trevo.view.types.OnItemClickListener

class ListProductAdapter(
    private val context: Context,
    private val products: List<Product>,
    private val cardType: String
) : RecyclerView.Adapter<ListProductAdapter.ViewHolder>() {


    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            val itemViewClick = if (cardType == "cardOrder") {
                itemView.findViewById<ImageButton>(R.id.cardButtonOrder)
            } else {
                itemView.findViewById<Button>(R.id.cardButton)
            }

            itemViewClick.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }

        fun vincula(product: Product) {
            var title = itemView.findViewById<TextView>(R.id.cardTitle);
            var img = itemView.findViewById<ImageView>(R.id.cardImage)

            if (cardType.equals("cardOrder")) {
                title = itemView.findViewById<TextView>(R.id.cardTitleOrder);
                img = itemView.findViewById<ImageView>(R.id.cardImageOrder)
            }
            title.text = product.nome;

            try {
                Glide.with(itemView.context)
                    .load("http://10.0.0.43:8080/trevo/api/produto/foto/" + product.imagem)
                    .into(img)
            } catch (e: GlideException) {
                Log.d("TAG", e.toString())
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.product_item, parent, false)
        if (cardType.equals("cardOrder")) {
            view = inflater.inflate(R.layout.product_item_order, parent, false)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.vincula(product)
    }

}
