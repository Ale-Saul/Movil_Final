package com.example.proyectofinal

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.network.MovieRemoteDataSource
import com.example.network.MovieResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel: ViewModel() {
    val list: LiveData<List<MovieResponseDto>>
        get() = _list
    private val _list = MutableLiveData<List<MovieResponseDto>>(emptyList())

    fun getAllMovies(dataSource: MovieRemoteDataSource, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = dataSource.getListResponse()
                withContext(Dispatchers.Main){
                    _list.value = response.results
                }
            }catch (e: Exception) {
                Log.e("Loading", "Error al cargar peliculas")
            }
        }
    }

    //val filteredList: MutableLiveData<List<Movie>> = MutableLiveData()

//    fun insertMovies(movies: List<Movie>) = viewModelScope.launch(Dispatchers.IO) {
//        movieDao.insertMovies(movies)
//    }
//
//    fun getMoviesByGenre(genreId: Int) = viewModelScope.launch(Dispatchers.IO) {
//        val movies = movieDao.getMoviesByGenre(genreId)
//        withContext(Dispatchers.Main){
//            filteredList.value = movies
//        }
//    }
}