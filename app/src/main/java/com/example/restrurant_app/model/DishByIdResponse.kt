package com.example.restrurant_app.model

data class DishByIdResponse(
    val response_code: Int,
    val outcome_code: Int,
    val response_message: String,
    val cuisine_id: String,
    val cuisine_name: String,
    val cuisine_image_url: String,
    val item_id: Int,
    val item_name: String,
    val item_price: Double,
    val item_rating1: Float,
    val item_rating: Float,
    val item_image_url: String
)
