package com.example.network;

import retrofit2.http.GET;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0006\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0007"}, d2 = {"Lcom/example/network/IApiListService;", "", "getListMovies", "Lcom/example/network/MoviesResponseDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getListMoviesPartThree", "getListMoviesPartTwo", "network_release"})
public abstract interface IApiListService {
    
    @retrofit2.http.GET(value = "discover/movie?include_adult=false&page=1&sort_by=popularity.desc&api_key=5740e9fe49b8252e514c6daa48d6463a&language=es")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getListMovies(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.network.MoviesResponseDto> $completion);
    
    @retrofit2.http.GET(value = "discover/movie?include_adult=false&page=2&sort_by=popularity.desc&api_key=5740e9fe49b8252e514c6daa48d6463a&language=es")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getListMoviesPartTwo(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.network.MoviesResponseDto> $completion);
    
    @retrofit2.http.GET(value = "discover/movie?include_adult=false&page=3&sort_by=popularity.desc&api_key=5740e9fe49b8252e514c6daa48d6463a&language=es")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getListMoviesPartThree(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.network.MoviesResponseDto> $completion);
}