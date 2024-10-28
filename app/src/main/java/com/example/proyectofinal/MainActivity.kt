package com.example.proyectofinal


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyectofinal.ui.theme.AppTheme
import com.example.proyectofinal.ui.theme.primaryContainerLightMediumContrast

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MovieScreen()
            }
        }
    }
}

@Composable
fun MovieCard(title: String, rating: Double, imageModel: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(width = 170.dp, height = 235.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF2E4564))
        ) {
            AsyncImage(
                model = imageModel,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(180.dp)
                    .width(160.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            )
            Row (
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .horizontalScroll(rememberScrollState())
                )
                Text(
                    text = "$rating",
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFFFA726),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

        }
    }
}

@Composable
fun MovieSection(title: String, movies: List<Pair<String, Double>>) {
    Column {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow {
            items(movies.size) { index ->
                MovieCard(title = movies[index].first, rating = movies[index].second, imageModel = "https://m.media-amazon.com/images/S/pv-target-images/79194981293eabf6620ece96eb5a9c1fffa04d3374ae12986e0748800b37b9cf.jpg" ) // Usa una imagen de recurso
            }
        }
    }
}

@Composable
fun PromocionesButton() {
    Button(
        onClick = { /* Acción */ },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
    ) {
        Text("PROMOCIONES", color = Color(0xFF2E4564), fontWeight = FontWeight.Bold)
    }
}

@Composable
fun MovieScreen() {
    Scaffold(
        contentColor = Color(0xFF2E4564)
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .background(primaryContainerLightMediumContrast)
            .padding(innerPadding)) {
            MovieSection(
                title = "Te podría interesar",
                movies = listOf(
                    "Interestelar" to 8.1,
                    "The Space Between Us" to 6.2,
                    "Pretty woman" to 5.1
                )
            )
            MovieSection(
                title = "Acción",
                movies = listOf(
                    "Búsqueda Implacable" to 7.1,
                    "Fallout" to 6.2
                )
            )
            PromocionesButton()
        }
    }
}