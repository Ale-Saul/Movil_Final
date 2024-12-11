package com.example.proyectofinal.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.example.model.Movie
import com.example.network.MovieRemoteDataSource
import com.example.network.RetrofitBuilder
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.theme.onPrimaryDark
import com.example.proyectofinal.ui.theme.onPrimaryLight
import com.example.proyectofinal.ui.theme.primaryContainerLightMediumContrast
import com.example.proyectofinal.ui.theme.tertiaryCommon
import com.example.proyectofinal.viewModel.MovieHomeViewModel
import com.example.repository.MovieRepository

@Composable
fun FavoritesScreen(onClick: (Int) -> Unit) {
    Scaffold(
        content = {paddingValues ->
            FavoriteMovieScreen(
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
                onClickMovie = onClick
            )
        }
    )
}

@Composable
fun FavoriteMovieScreen(modifier: Modifier, onClickMovie: (Int) -> Unit) {
    val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(RetrofitBuilder)
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val repository = MovieRepository(context)
    val moviesHomeViewModel: MovieHomeViewModel = MovieHomeViewModel(repository, dataSource)
    val listMovies by moviesHomeViewModel.movies.observeAsState(emptyList())
    //val listMovies by moviesHomeViewModel.movies.collectAsState(initial = emptyList())
    val favoriteMovies = listMovies.filter { it.isFavorite }

    LaunchedEffect(Unit) {
        moviesHomeViewModel.getAllMovies()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(primaryContainerLightMediumContrast)
            .padding(8.dp)
    ) {
            Text(
                text = stringResource(id = R.string.label_interes),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = onPrimaryLight,
                modifier = Modifier.padding(8.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                items(favoriteMovies.size) { it ->
                        MovieCard(
                            onClick = onClickMovie,
                            movieId = listMovies[it].movieId,
                            title = listMovies[it].title,
                            rating = listMovies[it].voteAverage,
                            imageModel = "https://image.tmdb.org/t/p/w185/${ listMovies[it].posterPath}",
                            isFavorite = listMovies[it].isFavorite
                        )
                }
            }
    }

}