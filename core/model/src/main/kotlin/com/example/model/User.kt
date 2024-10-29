package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val birthDate: String,
    val email: String,
    val password: String,
    val genres: String
)