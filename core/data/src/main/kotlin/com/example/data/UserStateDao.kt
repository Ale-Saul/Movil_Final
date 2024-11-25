package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.UserState

@Dao
interface UserStateDao {
    @Query("SELECT isLoggedIn FROM user_state LIMIT 1")
    suspend fun isLoggedIn(): Boolean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setUserState(userState: UserState)

    @Query("DELETE FROM user_state")
    suspend fun deleteAll()

    @Query("SELECT user_state.id FROM user_state")
    suspend fun getUserState(): Int

}