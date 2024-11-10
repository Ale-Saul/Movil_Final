package com.example.model

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "genreId"])
data class UserGenreCrossRef (
    val userId: Int,
    val genreId: Int
)
