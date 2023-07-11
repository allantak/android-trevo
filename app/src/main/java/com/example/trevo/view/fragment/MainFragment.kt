package com.example.trevo.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.trevo.R
import com.example.trevo.model.Product
import com.example.trevo.service.MainRetrofit
import com.example.trevo.service.model.ProductResponse
import com.example.trevo.view.activity.DetailActivity
import com.example.trevo.view.recyclerview.ListProductAdapter
import com.example.trevo.view.types.OnItemClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), OnItemClickListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var products: List<Product>;
    private lateinit var recyclerView: RecyclerView
    private lateinit var search: androidx.appcompat.widget.SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        lifecycleScope.launch(IO) {
            listProduct()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = view.findViewById(R.id.recycleView)
        search = view.findViewById(R.id.searchViewProduct)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                if (!p0.isNullOrEmpty()) {

                    MainRetrofit().productService.getProduct(p0.toInt()).enqueue(object :
                        Callback<Product> {
                        override fun onResponse(call: Call<Product>, response: Response<Product>) {
                            if (response.isSuccessful) {
                                val product: Product? = response.body()
                                products = listOf(product) as List<Product>
                                displayProductList()
                            } else {
                                val errorCode: Int = response.code()
                                val errorMessage: String = response.message()
                                println(errorCode)
                                println(errorMessage)
                            }
                        }

                        override fun onFailure(call: Call<Product>, t: Throwable) {
                            println(t)
                        }
                    })

                }

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })




        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(position: Int) {
        ItemToDetail(position)
    }

    fun ItemToDetail(position: Int) {
        val context = requireContext()

        println(products[position].culturas)
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("produto_nome", products[position].nome)
        intent.putExtra("produto_img", products[position].imagem)
        intent.putExtra("produto_capa", products[position].capa)
        intent.putExtra("produto_id", products[position].idProduto.toString())
        intent.putExtra("produto_descricao", products[position].descricao)
        intent.putExtra("produto_area", products[position].area)
        intent.putExtra("produto_culturas", products[position].culturas)
        startActivity(intent)
    }


    private fun displayProductList() {
        var adapter = ListProductAdapter(requireContext(), products = products, "card")
        adapter.setOnItemClickListener(this@MainFragment)
        recyclerView?.adapter = adapter
    }

    suspend fun listProduct() {
        println("Entrou")
        val call: Call<ProductResponse> = MainRetrofit().productService.listProduct()
        val response: Response<ProductResponse> = call.execute()

        if (response.isSuccessful) {
            val productResponse: ProductResponse? = response.body()

            if (productResponse != null) {
                products = productResponse.content

                withContext(Dispatchers.Main) {
                    displayProductList()
                }
            }
        }

    }
}