package com.example.restrurant_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import com.example.restrurant_app.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.restrurant_app.model.Cuisine

@Composable
fun CuisineCard(cuisine: Cuisine, onClick: () -> Unit, modifier: Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp


    val imageRes = when (cuisine.cuisine_name) {
        "North Indian" -> R.drawable.northindian
        "Chinese" -> R.drawable.chinese
        "Mexican" -> R.drawable.mexican
        "Italian" -> R.drawable.italian
        "Bengali" -> R.drawable.bengali
        "South Indian" -> R.drawable.southindian
        else -> R.drawable.placeholder
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(screenWidth)
            .height(1000.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = cuisine.cuisine_name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                        )
                    )
            )
            Text(
                text = cuisine.cuisine_name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            )
        }
    }
}
