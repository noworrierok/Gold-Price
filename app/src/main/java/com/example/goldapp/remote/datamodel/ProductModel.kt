package com.example.goldapp.remote.datamodel

data class ProductModel(
    val message: String,
    val data: AllData
)

data class AllData(
    val golds: List<ContentModel>,
    val currencies: List<ContentModel>
)

data class ContentModel(
    val label: String,
    val price: Int
)