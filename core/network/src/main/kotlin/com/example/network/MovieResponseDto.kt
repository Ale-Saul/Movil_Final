package com.example.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponseDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val poster_path: String,
    @Json(name = "genre_ids")
    val genre_ids: List<Int>,
    @Json(name = "vote_average")
    val  vote_average: Double
){}
