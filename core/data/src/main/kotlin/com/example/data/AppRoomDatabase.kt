package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.model.Genre
import com.example.model.Movie
import com.example.model.MovieGenreCrossRef
import com.example.model.User
@Database(entities = [User::class, Movie::class, Genre::class, MovieGenreCrossRef::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun userDao(): IUserDao
    abstract fun movieDao(): IMovieDao

    companion object {
        @Volatile
        private var Instance: AppRoomDatabase? = null
        fun getDatabase(context: Context): AppRoomDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppRoomDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}