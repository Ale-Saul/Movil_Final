package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.Genre
import com.example.model.User
import com.example.model.UserGenreCrossRef

@Dao
interface IUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserGenreCrossRef(userGenreCrossRef: UserGenreCrossRef)

    @Query("SELECT genreId FROM genre_table WHERE name = :name")
    suspend fun getGenreIdByName(name: String): Int

    @Query("SELECT COUNT(*) FROM user_table WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String) : Int

    @Query("SELECT COUNT(*) FROM user_table WHERE username = :username AND password = :password")
    suspend fun loginUser2(username: String, password: String) : Int

    @Query("SELECT * FROM user_table WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT userId FROM user_table WHERE username = :username")
    suspend fun getIdByUsername(username: String): Int
}