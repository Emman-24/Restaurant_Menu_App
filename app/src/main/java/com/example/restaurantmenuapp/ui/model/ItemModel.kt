package com.example.restaurantmenuapp.ui.model

data class ItemModel(
    val id: Int,
    val name: String,
    var stock: Int = 0,
)
