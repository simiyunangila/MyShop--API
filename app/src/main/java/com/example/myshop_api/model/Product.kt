package com.example.myshop_api.model

data class product(
    var id: Int,
    var title: String,
    var description: String,
    var Price: Double,
    var rating: String,
    var stock: Int,
    var category: String,
    var thumbnail: String
)
