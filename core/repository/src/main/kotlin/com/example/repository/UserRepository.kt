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

    suspend fun saveLoginState(userIdCurrent: Int, isLoggedIn: Boolean) {
        stateDao.deleteAll()
        stateDao.setUserState(UserState(id = userIdCurrent, isLoggedIn = isLoggedIn))
    }

    suspend fun getLoginState(): Int {
        return stateDao.getUserState()
    }

    suspend fun getIdByUsername(name: String): Int {
        return userDao.getIdByUsername(name)
    }

    fun getInfoUser(): User {
        return stateDao.getInfoUser()
    }
}

