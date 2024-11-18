package com.example.repository

import android.content.Context
import com.example.data.AppRoomDatabase
import com.example.model.User
import com.example.model.UserGenreCrossRef
import com.example.model.UserState

class UserRepository(val context: Context) {
    val userDao = AppRoomDatabase.getDatabase(context).userDao()
    val stateDao = AppRoomDatabase.getDatabase(context).stateDao()


    suspend fun insert(user: User): Long {
        return userDao.insertUser(user)
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return (userDao.loginUser2(email, password) > 0);
    }

    suspend fun getGenreIdByName(genreName: String): Int {
        val formattedGenreName = genreName.map {
                char ->
            when(char) {
                'ó' -> 'o'
                'á' -> 'a'
                'é' -> 'e'
                'í' -> 'i'
                'ú' -> 'u'
                else -> char
            }
        }.joinToString("")
        return userDao.getGenreIdByName(formattedGenreName)
    }

    suspend fun insertUserGenreCrossRef(userGenreCrossRef: UserGenreCrossRef) {
        return userDao.insertUserGenreCrossRef(userGenreCrossRef)
    }

    suspend fun saveLoginState(isLoggedIn: Boolean) {
        stateDao.setUserState(UserState(isLoggedIn = isLoggedIn))
    }

    suspend fun getLoginState(database:AppRoomDatabase): Boolean {
        return stateDao.isLoggedIn() ?: false
    }
}

