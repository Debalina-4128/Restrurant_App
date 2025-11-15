package com.example.restrurant_app.network

import com.example.restrurant_app.model.CuisineResponse
import com.example.restrurant_app.model.DishByIdResponse
import com.example.restrurant_app.model.PaymentRequest
import com.example.restrurant_app.model.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FoodApiService {
    @POST("/emulator/interview/get_item_list")
    @Headers(
        "X-Partner-API-Key: uonebancservceemultrS3cg8RaL30",
        "X-Forward-Proxy-Action: get_item_list",
        "Content-Type: application/json"
    )
    suspend fun getItemList(@Body body: Map<String, Int>): CuisineResponse

    @POST("/emulator/interview/get_item_by_id")
    @Headers(
        "X-Partner-API-Key: uonebancservceemultrS3cg8RaL30",
        "X-Forward-Proxy-Action: get_item_by_id",
        "Content-Type: application/json"
    )
    suspend fun getItemById(@Body body: Map<String, Int>): DishByIdResponse

    @POST("/emulator/interview/get_item_by_filter")
    @Headers(
        "X-Partner-API-Key: uonebancservceemultrS3cg8RaL30",
        "X-Forward-Proxy-Action: get_item_by_filter",
        "Content-Type: application/json"
    )
    suspend fun filterItems(@Body filters: Map<String, Any>): CuisineResponse

    @POST("/emulator/interview/make_payment")
    suspend fun makePayment(
        @Body request: PaymentRequest,
        @Header("X-Partner-API-Key") apiKey: String = "uonebancservceemultrS3cg8RaL30",
        @Header("X-Forward-Proxy-Action") action: String = "make_payment"
    ): PaymentResponse



}