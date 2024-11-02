package com.example.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.data.AppRoomDatabase
import com.example.model.Genre
import com.example.model.GenreWithMovies
import com.example.model.Movie
import com.example.model.MovieGenreCrossRef
import com.example.model.MovieWithGenres
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepository(val context: Context)  {
    val movieDao = AppRoomDatabase.getDatabase(context).movieDao()

    init {
        initializeGenres()
    }

    private fun initializeGenres() {
        CoroutineScope(Dispatchers.IO).launch {
            val genresInDb = movieDao.getAllGenres()
            if(genresInDb.isEmpty()) {
                val genres = listOf(
                    Genre(genreId = 28, name = "Accion"),
                    Genre(genreId = 16, name = "Animacion"),
                    Genre(genreId = 12, name = "Aventura"),
                    Genre(genreId = 878, name = "Ciencia Ficcion"),
                    Genre(genreId = 35, name = "Comedia"),
                    Genre(genreId = 99, name = "Documental"),
                    Genre(genreId = 18, name = "Drama"),
                    Genre(genreId = 14, name = "Fantasia"),
                    Genre(genreId = 10751, name = "Familiar-Infantil"),
                    Genre(genreId = 10402, name = "Musical"),
                    Genre(genreId = 9648, name = "Suspenso"),
                    Genre(genreId = 53, name = "Terror")
                )
                movieDao.insertGenres(genres)
            }
        }
    }
    suspend fun insert(movies: List<Movie>) {
        return movieDao.insertMovies(movies)
    }

    suspend fun getMoviesFilteredByGenre(genreId: Int) : List<GenreWithMovies>{
        return movieDao.getGenreWithMovies(genreId)
    }

    suspend fun insertGenres(genres: List<Genre>) {
        return movieDao.insertGenres(genres)
    }

    fun getAllMovies(): LiveData<List<Movie>> {
        return movieDao.getAllMovies()
    }

    suspend fun isMovieListEmpty(): Boolean {
        return movieDao.getMovieCount() == 0
    }

    suspend fun insertMovieGenreCrossRef(crossRef: List<MovieGenreCrossRef>) {
        return movieDao.insertMovieGenreCrossRef(crossRef)
    }

    suspend fun getMovieWithGenres(movieId: Int): List<MovieWithGenres> {
        return movieDao.getMovieWithGenres(movieId)
    }
}