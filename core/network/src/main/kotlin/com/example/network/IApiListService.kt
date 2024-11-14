package com.example.network

import retrofit2.http.GET

interface IApiListService {
    @GET("discover/movie?include_adult=false&page=1&sort_by=popularity.desc&api_key=5740e9fe49b8252e514c6daa48d6463a&language=es")
    suspend fun getListMovies(): MoviesResponseDto
}