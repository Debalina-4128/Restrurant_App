package com.example.restrurant_app.model

data class CartItem(
    val dish: DishByIdResponse,
    var quantity: Int = 1
)