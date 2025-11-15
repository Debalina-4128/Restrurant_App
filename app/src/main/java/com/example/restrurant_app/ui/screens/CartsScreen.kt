package com.example.restrurant_app.ui.screens

import CustomTopAppBar
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.restrurant_app.nav.Screen
import com.example.restrurant_app.viewmodel.MainViewModel

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val cartItems = viewModel.cartItems

    // Calculations based on quantities in cart
    val netTotal = cartItems.sumOf { it.dish.item_price * it.quantity }
    val tax = netTotal * 0.025
    val grandTotal = netTotal + (2 * tax)

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Cart", canNavigateBack = true) {
                navController.popBackStack()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("🛒 Cart", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            if (cartItems.isEmpty()) {
                Text("Your cart is empty.")
            } else {
                LazyColumn {
                    items(cartItems) { cartItem ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(cartItem.dish.item_name)
                            Text("x${cartItem.quantity}")
                            Row {
                                IconButton(onClick = { viewModel.addToCart(cartItem.dish) }) {
                                    Icon(Icons.Default.Add, contentDescription = "Add")
                                }
                                IconButton(onClick = { viewModel.removeFromCart(cartItem.dish) }) {
                                    Icon(Icons.Default.Remove, contentDescription = "Remove")
                                }
                            }
                        }
                    }
                }

            }

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    SummaryRow("Net Total", netTotal)
                    SummaryRow("CGST (2.5%)", tax)
                    SummaryRow("SGST (2.5%)", tax)
                    Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
                    SummaryRow("Grand Total", grandTotal, bold = true)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val paymentRequest = viewModel.preparePaymentRequest()
                        viewModel.makePaymentRequest(
                            paymentRequest = paymentRequest,
                            onSuccess = { txnRef ->
                                Toast.makeText(context, "Payment Successful! Ref: $txnRef", Toast.LENGTH_LONG).show()
                                navController.navigate(Screen.Home.route)
                            },
                            onError = { error ->
                                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                            }
                        )

                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Place Order")
                }
            }
        }
    }




@Composable
fun SummaryRow(label: String, amount: Double, bold: Boolean = false) {
    val style = if (bold) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = style)
        Text("₹${"%.2f".format(amount)}", style = style)
    }
}
