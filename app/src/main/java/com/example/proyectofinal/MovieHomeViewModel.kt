package com.example.proyectofinal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Movie
import com.example.model.MovieGenreCrossRef
import com.example.network.MovieRemoteDataSource
import com.example.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieHomeViewModel(
    private val repository: MovieRepository,
    private val dataSource: MovieRemoteDataSource
) : ViewModel()  {

    val movies: LiveData<List<Movie>> = repository.getAllMovies()

    init {
        fetchMoviesIfNecessary()
    }

    private fun fetchMoviesIfNecessary() {
        viewModelScope.launch {
            if (movies.value.isNullOrEmpty()) {
                fetchMovies()
            }
        }
    }

    private suspend fun fetchMovies() {
        try {
            val response = dataSource.getListResponse()
            repository.insert(response.results.map {it.toMovie()})
            response.results.forEach {
                movie -> movie.genre_ids.forEach {
                    genreId -> repository.insertMovieGenreCrossRef(
                        listOf(
                            MovieGenreCrossRef(movie.id.toInt(), genreId)
                    ))
                }
            }
        }catch (e: Exception) {
            Log.e("Loading", "Error al cargar peliculas")
        }
    }

    suspend fun getMoviesFilteredByGenre(genreId: Int) = repository.getMoviesFilteredByGenre(genreId)

    fun saveMovies(movies: List<Movie>) {
        viewModelScope.launch {
            repository.insert(movies)
        }
    }

    fun getMoviesByGenre(genreId: Int) {
        viewModelScope.launch {
            repository.getMoviesFilteredByGenre(genreId)
        }
    }

    fun getAllMovies() {
        viewModelScope.launch {
            repository.getAllMovies()
        }

    }


//    sealed class MovieHomeState {
//        class Loaded(val listMovies: LiveData<List<Movie>>) : MovieHomeState()
//    }
//
//    val state : LiveData<MovieHomeState>
//        get() = _state
//
//    private val _state = MutableLiveData<MovieHomeState>()
//
//    fun loadMovies() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val listMovies = repository.getAllMovies()
//            withContext(Dispatchers.Main) {
//                _state.value = MovieHomeState.Loaded(listMovies)
//            }
//
//        }
//    }

}