package com.example.proyectofinal.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoritesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D4B6E)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Tus Películas Favoritas",
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}