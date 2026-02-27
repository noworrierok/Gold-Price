package com.example.goldapp.remote.golds

import com.example.goldapp.remote.datamodel.ProductModel

interface ProductRequest {

    fun onSuccess(data : ProductModel)

    fun onNotSuccess(massage : String)

    fun onError(error : String)
}