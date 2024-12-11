package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey val movieId: Int,
    val title: String,
    val description: String,
    val posterPath: String,
    val voteAverage: Double,
    var isFavorite: Boolean
)