package com.example.proyectofinal.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.model.Cinema
import androidx.compose.material3.Scaffold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaDetailScreen(cinema: Cinema, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = cinema.name, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .background(Color(0xFF0B132B))
        ) {
            // Imagen del cine
            Image(
                painter = rememberImagePainter(cinema.imageUrl),
                contentDescription = cinema.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Información del cine
            Text(
                text = "Horario: ${cinema.openingHours} - ${cinema.closingHours}",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Calificación: ${cinema.rating}⭐",
                color = Color.Yellow,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Teléfono: ${cinema.phoneNumber}",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Imagen adicional donde irá el mapa en el futuro
            Image(
                painter = rememberImagePainter(cinema.imageUrl), // Usa la misma imagen por ahora
                contentDescription = "Ubicación de ${cinema.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para abrir en Google Maps (si quieres mantenerlo)
            Button(
                onClick = {
                    /*val gmmIntentUri = Uri.parse("geo:${cinema.latitude},${cinema.longitude}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)*/
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3A506B),
                    contentColor = Color.White
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Abrir en Google Maps")
            }
        }
    }
}