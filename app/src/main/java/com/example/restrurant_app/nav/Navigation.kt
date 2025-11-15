package com.example.restrurant_app.nav


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")

    object Cuisine : Screen("cuisine/{cuisineId}") {
        fun createRoute(cuisineId: String): String = "cuisine/$cuisineId"
    }
}
