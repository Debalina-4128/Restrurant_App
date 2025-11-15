package com.example.restrurant_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFD32F2F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB71C1C),
    secondary = Color(0xFFFFC107),
    onSecondary = Color.Black,
    background = Color.White,
    surface = Color(0xFFF5F5F5),
    onSurface = Color(0xFF212121),
    error = Color(0xFFF44336),
    onError = Color.White,
)


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF8A65),        // Soft orange
    onPrimary = Color.Black,
    primaryContainer = Color(0xFFD84315),
    secondary = Color(0xFFFFC107),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    error = Color(0xFFFF5252),
    onError = Color.Black
)


@Composable
fun Restrurant_AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}