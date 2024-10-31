package com.example.proyectofinal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Movie
import com.example.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieHomeViewModel(private val repository: MovieRepository) : ViewModel()  {
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
}