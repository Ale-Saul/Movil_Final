package com.example.proyectofinal.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Movie
import com.example.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieInterestViewModel(
    private val repository: MovieRepository
) : ViewModel()  {
    val moviesInterest: LiveData<List<Pair<String, List<Movie>?>>>
        get() = _moviesInterest
    val _moviesInterest = MutableLiveData<List<Pair<String, List<Movie>?>>>()

    fun getMovieById(movieId: Int): LiveData<Movie> {
        return repository.getMovieById(movieId)
    }

    fun getMoviesFilteredByGenre(isDataLoaded : Boolean) {
        viewModelScope.launch (Dispatchers.IO){
            if( isDataLoaded ) {
                val genresAll = repository.getAllGenres()
                val moviesFiltered = genresAll.map { genre ->
                    (genre.name to repository.getMoviesFilteredByGenre(genre.genreId))
                }
                withContext(Dispatchers.Main) {
                    _moviesInterest.value = moviesFiltered
                }
            } else {
                Log.e("MovieHomeViewModel", "No se han cargado las peliculas")
            }
        }
    }



}