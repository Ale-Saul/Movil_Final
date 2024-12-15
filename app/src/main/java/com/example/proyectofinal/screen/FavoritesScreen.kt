package com.example.proyectofinal.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.model.Movie
import com.example.network.MovieRemoteDataSource
import com.example.network.RetrofitBuilder
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.theme.onPrimaryLight
import com.example.proyectofinal.ui.theme.primaryContainerLightMediumContrast
import com.example.proyectofinal.ui.theme.tertiaryCommon
import com.example.proyectofinal.viewModel.MovieHomeViewModel
import com.example.proyectofinal.viewModel.MovieInterestViewModel
import com.example.repository.MovieRepository

@Composable
fun FavoritesScreen(onClick: (Int) -> Unit, onNavigateToCinemas: () -> Unit) {
    Scaffold(
        content = {paddingValues ->
            FavoriteMovieScreen(
                modifier = Modifier.padding(paddingValues),
                onClickMovie = onClick,
                onNavigateToCinemas = onNavigateToCinemas
            )
        }
    )
}

@Composable
fun FavoriteMovieScreen(modifier: Modifier, onClickMovie: (Int) -> Unit, onNavigateToCinemas: () -> Unit) {
    val dataSource: MovieRemoteDataSource = MovieRemoteDataSource(RetrofitBuilder)
    val context = LocalContext.current
    val repository = MovieRepository(context)

    val moviesHomeViewModel: MovieHomeViewModel = MovieHomeViewModel(repository, dataSource)
    val listMovies by moviesHomeViewModel.favoriteMovies.observeAsState(emptyList())
    val movieInterestViewModel: MovieInterestViewModel = MovieInterestViewModel()
    var moviesUI by remember { mutableStateOf(listOf<Pair<String, List<Movie>>>()) }
    movieInterestViewModel.getMoviesFilteredByGenre(LocalContext.current, true)

    LaunchedEffect(Unit) {
        moviesHomeViewModel.getFavoriteMovies()
    }

    fun updateUI(moviesInterestState: MovieInterestViewModel.MoviesInterestState) {
        when(moviesInterestState) {
            is MovieInterestViewModel.MoviesInterestState.Loading -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }
            is MovieInterestViewModel.MoviesInterestState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is MovieInterestViewModel.MoviesInterestState.Success -> {

                moviesUI = moviesInterestState.movies
            }
        }
    }
    movieInterestViewModel.moviesInterest.observe(
        LocalLifecycleOwner.current,
        Observer(::updateUI)
    )
    Box(
        modifier= Modifier
            .fillMaxSize()
            .background(primaryContainerLightMediumContrast)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.label_favoritos),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = onPrimaryLight,
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                items(listMovies.size) { it ->
                    MovieCard(
                        onClick = onClickMovie,
                        movieId = listMovies[it].movieId,
                        title = listMovies[it].title,
                        rating = listMovies[it].voteAverage,
                        imageModel = "https://image.tmdb.org/t/p/w185/${listMovies[it].posterPath}",
                    )
                }
            }
        }
    }
}