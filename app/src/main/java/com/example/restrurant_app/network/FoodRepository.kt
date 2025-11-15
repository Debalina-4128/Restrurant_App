package com.example.restrurant_app.network

import com.example.restrurant_app.model.CuisineResponse
import com.example.restrurant_app.model.DishByIdResponse
import com.example.restrurant_app.model.PaymentRequest
import com.example.restrurant_app.model.PaymentResponse

open class FoodRepository(private val api: FoodApiService) {

    open suspend fun getCuisines(page: Int = 1, count: Int = 10): CuisineResponse {
        return api.getItemList(mapOf("page" to page, "count" to count))
    }

    suspend fun getItemById(id : Int): DishByIdResponse{
        return api.getItemById(mapOf("item_id" to id))
    }

    suspend fun filterItems(
        cuisines: List<String>? = null,
        minPrice: Int? = null,
        maxPrice: Int? = null,
        rating: Int? = null
    ): CuisineResponse {
        val filters = mutableMapOf<String, Any>()
        cuisines?.let { filters["cuisine_type"] = it }
        if (minPrice != null && maxPrice != null) {
            filters["price_range"] = mapOf("min_amount" to minPrice, "max_amount" to maxPrice)
        }
        rating?.let { filters["min_rating"] = it }
        return api.filterItems(filters)
    }

    suspend fun makePayment(request: PaymentRequest): PaymentResponse {
        return api.makePayment(request)
    }
}
