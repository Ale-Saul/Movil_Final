package com.example.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.model.Genre
import com.example.model.GenreWithMovies
import com.example.model.Movie
import com.example.model.MovieGenreCrossRef
import com.example.model.MovieWithGenres

@Dao
interface IMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM genre_table")
    fun getAllGenres(): List<Genre>

    @Query("SELECT COUNT(*) FROM movie_table")
    suspend fun getMovieCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenreCrossRef(crossRef: List<MovieGenreCrossRef>)

    @Transaction
    @Query("SELECT * FROM movie_table WHERE movieId = :movieId")
    suspend fun getMovieWithGenres(movieId: Int): List<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM genre_table WHERE genreId = :genreId")
    suspend fun getGenreWithMovies(genreId: Int): List<GenreWithMovies>

    @Query("SELECT * FROM movie_table WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<Movie>

    @Query("UPDATE movie_table SET isFavorite = :isFavorite WHERE movieId = :movieId")
    suspend fun updateFavorite(movieId: Int , isFavorite: Boolean)

}