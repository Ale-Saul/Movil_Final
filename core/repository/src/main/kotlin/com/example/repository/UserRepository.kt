package com.example.repository

import android.content.Context
import com.example.data.AppRoomDatabase
import com.example.model.User
import com.example.model.UserGenreCrossRef

class UserRepository(val context: Context) {
    val userDao = AppRoomDatabase.getDatabase(context).userDao()

    suspend fun insert(user: User): Long {
        return userDao.insertUser(user)
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return userDao.loginUser(email, password) > 0
    }

    suspend fun getGenreIdByName(genreName: String): Int {
        return userDao.getGenreIdByName(genreName)
    }

    suspend fun insertUserGenreCrossRef(userGenreCrossRef: UserGenreCrossRef) {
        return userDao.insertUserGenreCrossRef(userGenreCrossRef)
    }
}