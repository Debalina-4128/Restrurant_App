package com.example.restrurant_app.model

data class PaymentItem(
    val cuisine_id: Int,
    val item_id: Int,
    val item_price: Double,
    val item_quantity: Int
)

data class PaymentRequest(
    val total_amount: String,
    val total_items: Int,
    val data: List<PaymentItem>
)

data class PaymentResponse(
    val response_code: Int,
    val outcome_code: Int,
    val response_message: String,
    val txn_ref_no: String
)
