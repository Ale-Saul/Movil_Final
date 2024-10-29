package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val genresId: String,
    val description: String,
    val poster_path: String,
    val vote_average: Double
)