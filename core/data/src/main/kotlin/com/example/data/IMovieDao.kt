package com.example.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.model.Genre
import com.example.model.GenreWithMovies
import com.example.model.Movie
import com.example.model.MovieGenreCrossRef
import com.example.model.MovieWithGenres
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT genre_table.* FROM UserGenreCrossRef JOIN genre_table ON UserGenreCrossRef.genreId = genre_table.genreId WHERE UserGenreCrossRef.userId = 2")
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
    @Query("SELECT movie_table.* FROM movie_table JOIN MovieGenreCrossRef ON movie_table.movieId = MovieGenreCrossRef.movieId WHERE genreId = :genreId")
    fun getGenreWithMovies(genreId: Int): Flow<List<Movie>?>

    @Query("SELECT * FROM movie_table WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<Movie>

}