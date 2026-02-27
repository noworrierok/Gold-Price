package com.example.goldapp.remote.golds

import com.example.goldapp.remote.MainRetrofitService
import com.example.goldapp.remote.datamodel.ProductModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductApiRepository private constructor(){

    companion object{

        private var productApiRepository : ProductApiRepository? = null

        val instance: ProductApiRepository
            get() {
                if (productApiRepository == null) productApiRepository = ProductApiRepository()
                return productApiRepository!!
            }
    }

    fun getProducts(
        request: ProductRequest
    ){

        MainRetrofitService.productService.getProduct().enqueue(

            object : Callback<ProductModel>{
                override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {
                    if (response.isSuccessful){
                        val data = response.body() as ProductModel
                        request.onSuccess(data)
                    }else{
                        request.onNotSuccess(response.message())
                    }
                }

                override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                    request.onError("Error : ${t.message}")
                }
            }
        )
    }


}