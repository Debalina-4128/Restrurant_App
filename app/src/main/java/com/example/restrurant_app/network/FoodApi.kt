package com.example.restrurant_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FoodApi {
    private const val BASE_URL = "https://uat.onebanc.ai"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: FoodApiService = retrofit.create(FoodApiService::class.java)
}
