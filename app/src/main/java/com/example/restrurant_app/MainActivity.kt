package com.example.restrurant_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.restrurant_app.nav.Screen
import com.example.restrurant_app.network.FoodApi
import com.example.restrurant_app.network.FoodRepository
import com.example.restrurant_app.ui.screens.CartScreen
import com.example.restrurant_app.ui.screens.CuisineScreen
import com.example.restrurant_app.ui.screens.HomeScreen
import com.example.restrurant_app.ui.theme.Restrurant_AppTheme
import com.example.restrurant_app.viewmodel.MainViewModel
import com.example.restrurant_app.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Restrurant_AppTheme {
                val navController = rememberNavController()
                val factory = MainViewModelFactory(FoodRepository(FoodApi.retrofitService))
                val viewModel: MainViewModel = viewModel(factory = factory)

                NavHost(navController = navController, startDestination = Screen.Home.route) {

                    composable(Screen.Home.route) {
                        HomeScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }

                    composable(
                        route = Screen.Cuisine.route,
                        arguments = listOf(navArgument("cuisineId") {
                            type = NavType.IntType
                        })
                    ) { backStackEntry ->
                        val cuisineId = backStackEntry.arguments?.getInt("cuisineId")
                        CuisineScreen(
                            cuisineId = cuisineId.toString(),
                            navController = navController,
                            viewModel = viewModel// ✅ Added for cart support
                        )
                    }

                    composable(Screen.Cart.route) {
                        CartScreen(
                            navController = navController,
                            viewModel = viewModel // ✅ Removed unnecessary `dishes` param
                        )
                    }
                }
            }
        }
    }
}




