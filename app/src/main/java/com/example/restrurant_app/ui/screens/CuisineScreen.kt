package com.example.restrurant_app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restrurant_app.model.DishByIdResponse
import com.example.restrurant_app.ui.components.DishItem
import com.example.restrurant_app.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuisineScreen(
    cuisineId: String,
    navController: NavController,
    viewModel: MainViewModel
) {
    val allDishes = listOf(
        DishByIdResponse(
            response_code = 200,
            outcome_code = 200,
            response_message = "Fetched",
            cuisine_id = 1.toString(),
            cuisine_name = "North Indian",
            cuisine_image_url = "https://uat-static.onebanc.ai/picture/ob_cuisine_north_indian.webp",
            item_id = 1,
            item_name = "Butter Chicken",
            item_price = 249.0,
            item_rating1 = 4.5f,
            item_rating = 4.5f,
            item_image_url = "https://uat-static.onebanc.ai/picture/ob_dish_butter_chicken.webp"
        ),
        DishByIdResponse(
            response_code = 200,
            outcome_code = 200,
            response_message = "Fetched",
            cuisine_id = 2.toString(),
            cuisine_name = "Chinese",
            cuisine_image_url = "https://uat-static.onebanc.ai/picture/ob_cuisine_chinese.webp",
            item_id = 2,
            item_name = "Fried Rice",
            item_price = 199.0,
            item_rating1 = 4.2f,
            item_rating = 4.2f,
            item_image_url = "https://uat-static.onebanc.ai/picture/ob_dish_fried_rice.webp"
        ),
        DishByIdResponse(
            response_code = 200,
            outcome_code = 200,
            response_message = "Fetched",
            cuisine_id = 3.toString(),
            cuisine_name = "Mexican",
            cuisine_image_url = "https://uat-static.onebanc.ai/picture/ob_cuisine_mexican.webp",
            item_id = 3,
            item_name = "Tacos",
            item_price = 229.0,
            item_rating1 = 4.3f,
            item_rating = 4.3f,
            item_image_url = "https://uat-static.onebanc.ai/picture/ob_dish_tacos.webp"
        ),
        DishByIdResponse(
            response_code = 200,
            outcome_code = 200,
            response_message = "Fetched",
            cuisine_id = 4.toString(),
            cuisine_name = "Italian",
            cuisine_image_url = "https://uat-static.onebanc.ai/picture/ob_cuisine_italian.webp",
            item_id = 4,
            item_name = "Pizza",
            item_price = 229.0,
            item_rating1 = 4.3f,
            item_rating = 4.3f,
            item_image_url = "https://uat-static.onebanc.ai/picture/ob_dish_pizza.webp"
        ),
        DishByIdResponse(
            response_code = 200,
            outcome_code = 200,
            response_message = "Fetched",
            cuisine_id = 5.toString(),
            cuisine_name = "Bengali",
            cuisine_image_url = "https://uat-static.onebanc.ai/picture/ob_cuisine_bengali.webp",
            item_id = 5,
            item_name = "Sorshe Ilish",
            item_price = 229.0,
            item_rating1 = 4.3f,
            item_rating = 4.3f,
            item_image_url = "https://uat-static.onebanc.ai/picture/ob_dish_sorshe_ilish.webp"
        ),
        DishByIdResponse(
            response_code = 200,
            outcome_code = 200,
            response_message = "Fetched",
            cuisine_id = 6.toString(),
            cuisine_name = "South Indian",
            cuisine_image_url = "https://uat-static.onebanc.ai/picture/ob_cuisine_south_indian.webp",
            item_id = 6,
            item_name = "Dosa",
            item_price = 229.0,
            item_rating1 = 4.3f,
            item_rating = 4.3f,
            item_image_url = "https://uat-static.onebanc.ai/picture/ob_dish_dosa.webp"
        )
    )

    val filteredDishes = remember {
        viewModel.topDishes.filter { it.cuisine_id == cuisineId }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cuisine", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredDishes) { dish ->
                DishItem(dish = dish, viewModel = viewModel) // Make sure DishItem supports DishByIdResponse
            }
        }
    }
}
