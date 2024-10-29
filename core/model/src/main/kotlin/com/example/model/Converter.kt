package com.example.model

import androidx.room.TypeConverter
class Converter {
    @TypeConverter
    fun fromGenreList(genreList: List<String>):String {
        return genreList.joinToString(",")
    }
    @TypeConverter
    fun toGenreList(genres: String): List<String> {
        return genres.split(",")
    }
}