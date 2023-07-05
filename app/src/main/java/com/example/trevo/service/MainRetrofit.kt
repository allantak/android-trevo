package com.example.trevo.service

import com.example.trevo.service.services.ProductService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainRetrofit{
    private var retrofit = Retrofit.Builder()
        .baseUrl("http://172.29.32.1:8080/trevo/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    var productService = retrofit.create(ProductService::class.java);
}