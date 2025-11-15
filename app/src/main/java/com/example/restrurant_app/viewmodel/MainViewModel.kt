package com.example.restrurant_app.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restrurant_app.model.CartItem
import com.example.restrurant_app.model.Cuisine
import com.example.restrurant_app.model.DishByIdResponse
import com.example.restrurant_app.model.PaymentItem
import com.example.restrurant_app.model.PaymentRequest
import com.example.restrurant_app.network.FoodRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: FoodRepository
) : ViewModel() {

    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    var cuisines by mutableStateOf<List<Cuisine>>(emptyList())
        private set

    var topDishes by mutableStateOf<List<DishByIdResponse>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val result = repository.getCuisines()
                cuisines = result.cuisines

                topDishes = cuisines
                    .flatMap { cuisine ->
                        cuisine.items.map { item ->
                            DishByIdResponse(
                                response_code = 200,
                                outcome_code = 200,
                                response_message = "Fetched from cuisine",
                                cuisine_id = cuisine.cuisine_id,
                                cuisine_name = cuisine.cuisine_name,
                                cuisine_image_url = cuisine.cuisine_image_url,
                                item_id = item.id,
                                item_name = item.name,
                                item_price = item.price.toDouble(),
                                item_rating = item.rating.toFloat(),
                                item_rating1 = item.rating.toFloat(),
                                item_image_url = item.image_url
                            )
                        }
                    }
                    .sortedByDescending { it.item_rating }
                    .take(3)
            } catch (e: Exception) {
                errorMessage = e.message ?: "An error occurred"
            } finally {
                isLoading = false
            }
        }
    }

    fun preparePaymentRequest(): PaymentRequest {
        val data = cartItems.map {
            PaymentItem(
                cuisine_id = it.dish.cuisine_id.toIntOrNull() ?: 0,
                item_id = it.dish.item_id,
                item_price = it.dish.item_price,
                item_quantity = it.quantity
            )
        }


        val totalAmount = cartItems.sumOf { it.dish.item_price * it.quantity }
        val totalItems = cartItems.sumOf { it.quantity }

        val paymentRequest = PaymentRequest(
            total_amount = totalAmount.toInt().toString(),
            total_items = totalItems,
            data = data
        )
        Log.d("PaymentRequest", Gson().toJson(paymentRequest))

        return paymentRequest
    }

    fun makePaymentRequest(
        paymentRequest: PaymentRequest,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = repository.makePayment(paymentRequest)
                onSuccess(response.txn_ref_no)
                clearCart()
            } catch (e: Exception) {
                onError(e.message ?: "Payment failed")
            }
        }
    }


    fun getSelectedDishes(): List<DishByIdResponse> = cartItems.map { it.dish }

    fun addToCart(dish: DishByIdResponse) {
        val existing = _cartItems.find { it.dish.item_id == dish.item_id }
        if (existing != null) existing.quantity++ else _cartItems.add(CartItem(dish, 1))
    }

    fun removeFromCart(dish: DishByIdResponse) {
        val existing = _cartItems.find { it.dish.item_id == dish.item_id }
        if (existing != null) {
            if (existing.quantity > 1) existing.quantity--
            else _cartItems.remove(existing)
        }
    }

    fun clearCart() {
        _cartItems.clear()
    }
}
