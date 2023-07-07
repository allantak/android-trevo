package com.example.trevo.service.services

import com.example.trevo.model.Product
import com.example.trevo.service.model.Pedido
import com.example.trevo.service.model.ProductResponse
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductService {
    @GET("produtos")
    fun listProduct(): Call<ProductResponse>

    @GET("produto/{id}")
    fun getProduct(@Path("id") productId: Int): Call<Product>

    @POST("proposta")
    fun propose(@Body pedido: Pedido): Call<Void>
}