package com.example.repository

import android.content.Context
import com.example.data.AppRoomDatabase
import com.example.model.GenreWithMovies
import com.example.model.Movie

class MovieRepository(val context: Context)  {
    val movieDao = AppRoomDatabase.getDatabase(context).movieDao()

    suspend fun insert(movies: List<Movie>) {
        return movieDao.insertMovies(movies)
    }

    suspend fun getMoviesFilteredByGenre(genreId: Int) : List<GenreWithMovies>{
        return movieDao.getGenreWithMovies(genreId)
    }

}