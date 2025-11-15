package com.example.restrurant_app.ui.screens

import CustomTopAppBar
import TopDishCard
import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restrurant_app.R
import com.example.restrurant_app.nav.Screen
import com.example.restrurant_app.ui.components.CuisineCard
import com.example.restrurant_app.utils.updateLocale
import com.example.restrurant_app.viewmodel.MainViewModel
import java.util.*

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val cuisines = viewModel.cuisines
    val topDishes = viewModel.topDishes
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Scaffold(
        topBar = {
            CustomTopAppBar(title = stringResource(R.string.app_name))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                return@Column
            }

            error?.let {
                Text("Error: $it", color = MaterialTheme.colorScheme.error)
                return@Column
            }

            // Cuisines List
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                items(cuisines) { cuisine ->
                    CuisineCard(
                        cuisine = cuisine,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .width(300.dp),
                        onClick = {
                            navController.navigate(Screen.Cuisine.createRoute(cuisineId = cuisine.cuisine_id))
                        }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.top_dishes),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Top Dishes List
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
            ) {
                items(topDishes) { dish ->
                    TopDishCard(
                        dish = dish,
                        onAdd = {
                            viewModel.addToCart(dish)
                        }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Screen.Cart.route) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.go_to_cart),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Language Toggle
            var lang by remember { mutableStateOf("EN") }
            val context = LocalContext.current
            TextButton(onClick = {
                val locale = if (lang == "EN") Locale("hi") else Locale("en")
                lang = if (lang == "EN") "HI" else "EN"
                context.updateLocale(locale)
                (context as? Activity)?.recreate()
            }) {
                Text(text = stringResource(R.string.language) + ": $lang")
            }
        }
    }
}

