package com.example.restrurant_app.model

data class FilterRequest(
    val cuisine_type: List<String>? = null,
    val price_range: PriceRange? = null,
    val min_rating: Int? = null
)

data class PriceRange(
    val min_amount: Int,
    val max_amount: Int
)
