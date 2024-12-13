package com.example.proyectofinal.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.model.Cinema
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaDetailScreen(cinema: Cinema, onBackPressed: () -> Unit) {
    // Crear un estado para permitir el desplazamiento vertical
    val scrollState = rememberScrollState()

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
        // Columna que permitirá desplazarse verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .background(Color(0xFF0B132B))
                .verticalScroll(scrollState) // Activar el desplazamiento vertical
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

            // GoogleMap con la ubicación del cine
            val ucb = LatLng(cinema.latitude, cinema.longitude)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(ucb, 10f)
            }
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp), // Ajusta el tamaño del mapa
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = ucb),
                    title = cinema.name,
                    snippet = "Marker in ${cinema.name}"
                )
            }
        }
    }
}
