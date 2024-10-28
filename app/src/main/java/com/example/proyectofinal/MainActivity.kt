package com.example.proyectofinal


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.example.network.MovieRemoteDataSource
import com.example.network.MovieResponseDto
import com.example.network.MoviesResponseDto
import com.example.network.RetrofitBuilder
import com.example.proyectofinal.ui.theme.AppTheme
import com.example.proyectofinal.ui.theme.onPrimaryDark
import com.example.proyectofinal.ui.theme.onPrimaryLight
import com.example.proyectofinal.ui.theme.onSecondaryContainerLight
import com.example.proyectofinal.ui.theme.primaryContainerLightMediumContrast
import com.example.proyectofinal.ui.theme.secondaryLightMediumContrast
import com.example.proyectofinal.ui.theme.tertiaryCommon

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
                .background(onPrimaryDark)
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = onPrimaryLight,
                    modifier = Modifier
                        .widthIn(max = 130.dp)
                        .padding(8.dp)
                        .horizontalScroll(rememberScrollState())
                )
                Text(
                    text = "$rating",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = tertiaryCommon,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

        }
    }
}

@Composable
fun MovieSection(title: String, movies: List<MovieResponseDto>) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = onPrimaryLight,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow {
            items(movies.size) {
                MovieCard(
                    title = movies[it].title,
                    rating = movies[it].vote_average,
                    imageModel = "https://image.tmdb.org/t/p/w185/${movies[it].poster_path}"
                )
            }
        }
    }
}

@Composable
fun PromocionesButton() {
    OutlinedButton(
        onClick = { /* Acción */ },
        border = BorderStroke(1.dp, onPrimaryLight),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
    ) {
        Text(text = stringResource(id = R.string.promocion_label),
            color = onPrimaryLight,
            fontSize = 18.sp
        )
    }
}

@Composable
fun MovieScreen() {
    val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(RetrofitBuilder)
    val context = LocalContext.current
    var listAllMovies : MovieRemoteDataSource = MovieRemoteDataSource(RetrofitBuilder)
    val lifecycle = LocalLifecycleOwner.current
    var listMovies by remember { mutableStateOf<List<MovieResponseDto>>(emptyList())}
    val moviesViewModel = MoviesViewModel()

    fun updateUI(movieResponseDtos: List<MovieResponseDto>) {
        listMovies = movieResponseDtos
    }
    moviesViewModel.list.observe(
        lifecycle,
        Observer(::updateUI)
    )

    moviesViewModel.getAllMovies(dataSource, context)
    Scaffold(
        contentColor = Color(0xFF2E4564)
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .background(primaryContainerLightMediumContrast)
            .padding(innerPadding)) {
            MovieSection(
                title = stringResource(id = R.string.label_interes),
                movies = listMovies
            )
            MovieSection(
                title = stringResource(id = R.string.label_accion),
                movies = listMovies
            )
            PromocionesButton()
        }
    }
}