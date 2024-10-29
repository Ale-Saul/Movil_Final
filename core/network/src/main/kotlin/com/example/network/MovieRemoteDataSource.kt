package com.example.network

class MovieRemoteDataSource (
    val retrofitService: RetrofitBuilder
)
{
    suspend fun getListResponse(): MoviesResponseDto {
        return retrofitService.apiService.getListMovies()
    }
}