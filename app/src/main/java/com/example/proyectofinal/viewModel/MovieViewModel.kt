package com.example.proyectofinal.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Movie
import com.example.proyectofinal.screen.conversor
import com.example.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    /*val movies: StateFlow<List<Movie>> get() = _movies
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())*/

    val star : LiveData<Int>
        get() = _star
    private val _star = MutableLiveData<Int>()

    val icon_favorite : LiveData<Boolean>
        get() = _icon_favorite
    private val _icon_favorite = MutableLiveData<Boolean>()

    fun update(newRating: Int) {
        _star.value = newRating
    }

    fun add_favorite(movieId: Int, iconSelected: Boolean, repository: MovieRepository){
        Log.d("favoritos","cambioVM $movieId")
        viewModelScope.launch {
                repository.updateFavorite(movieId, iconSelected)
                _icon_favorite.value = iconSelected
            repository.getAllMovies()

        }
    }
    fun logi(movieId: Int, repository: MovieRepository){
        viewModelScope.launch {
            Log.d(
                "favorite",
                "lets see : ${repository.getAllMovies().value!!.get(movieId).isFavorite
                } ${repository.getAllMovies().value!!.get(movieId).title} "
            )
        }
    }
}