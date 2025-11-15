package com.example.restrurant_app.model

data class CuisineResponse(
    val cuisines: List<Cuisine>,
    val response_code: Int,
    val outcome_code: Int,
    val response_message: String,
    val page: Int?,
    val count: Int?,
    val total_pages: Int?,
    val total_items: Int?
)

data class Cuisine(
    val cuisine_id: String,
    val cuisine_name: String,
    val cuisine_image_url: String,
    val items: List<Dish>
)

data class Dish(
    val id: Int,
    val name: String,
    val image_url: String,
    val price: Double,
    val rating: Float
)

