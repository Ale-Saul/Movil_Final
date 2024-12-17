package com.example.proyectofinal.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.network.MovieRemoteDataSource
import com.example.network.RetrofitBuilder
import com.example.proyectofinal.viewModel.MovieHomeViewModel
import com.example.repository.MovieRepository
import com.example.model.Movie

@Composable
fun MovieSearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current

    val dataSource = MovieRemoteDataSource(RetrofitBuilder)
    val repository = MovieRepository(context)
    val moviesHomeViewModel = MovieHomeViewModel(repository, dataSource)

    val movies = moviesHomeViewModel.movies.observeAsState(emptyList()).value

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar Película") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        MovieList(movies = movies, query = searchQuery)
    }
}

@Composable
fun MovieList(movies: List<Movie>, query: String) {
    val filteredMovies = movies.filter {
        it.title.contains(query, ignoreCase = true)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(filteredMovies) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Text(
        text = movie.title,
        modifier = Modifier.padding(16.dp)
    )
}