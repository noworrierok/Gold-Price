package com.example.goldapp.remote.golds

import com.example.goldapp.remote.datamodel.ProductModel
import retrofit2.Call
import retrofit2.http.GET

interface ProductApiService {

    @GET("currencies")
    fun getProduct(): Call<ProductModel>
}