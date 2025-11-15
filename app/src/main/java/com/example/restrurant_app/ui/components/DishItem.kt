package com.example.restrurant_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.restrurant_app.model.DishByIdResponse
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import com.example.restrurant_app.model.NetworkImage
import com.example.restrurant_app.viewmodel.MainViewModel

@Composable
fun DishItem(
    dish: DishByIdResponse,
    viewModel: MainViewModel
) {
    // Get current quantity from cart
    val quantity = viewModel.cartItems.find { it.dish.item_id == dish.item_id }?.quantity ?: 0

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                url = dish.item_image_url,
                contentDescription = dish.item_name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = dish.item_name, style = MaterialTheme.typography.titleMedium)
                Text(text = "₹${dish.item_price}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Rating: ${dish.item_rating}", style = MaterialTheme.typography.labelMedium)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = { viewModel.removeFromCart(dish) },
                    enabled = quantity > 0
                ) {
                    Icon(Icons.Default.Remove, contentDescription = "Remove")
                }

                Text("$quantity", fontWeight = FontWeight.Bold)

                IconButton(onClick = { viewModel.addToCart(dish) }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    }
}
