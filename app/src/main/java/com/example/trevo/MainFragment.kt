package com.example.trevo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.trevo.model.Product
import com.example.trevo.service.MainRetrofit
import com.example.trevo.service.model.ProductResponse
import com.example.trevo.service.services.ProductService
import com.example.trevo.view.activity.DetailActivity
import com.example.trevo.view.recyclerview.ListProductAdapter
import com.example.trevo.view.types.OnItemClickListener
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.util.Objects

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var products: List<Product>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleView)

        lifecycleScope.launch(IO) {
            val call: Call<ProductResponse> = MainRetrofit().productService.listProduct()
            val response: Response<ProductResponse> = call.execute()

            if (response.isSuccessful) {
                val productResponse: ProductResponse? = response.body()

                if (productResponse != null) {
                    val products: List<Product> = productResponse.content
                    withContext(Dispatchers.Main) {
                        val adapter = ListProductAdapter(requireContext(), products)
                        adapter.setOnItemClickListener(this@MainFragment)
                        recyclerView.adapter = adapter
                    }
                }
            }
        }

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
        val intent = Intent(context, DetailActivity::class.java)
         intent.putExtra("produto_nome", products[position].nome)
         intent.putExtra("produto_img", products[position].imagem)
        startActivity(intent)
    }
}