package com.example.network;

import com.example.model.Movie;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

@com.squareup.moshi.JsonClass(generateAdapter = true)
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u000bH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\u000e\b\u0003\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0003\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\f\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020\tH\u00d6\u0001J\u0006\u0010$\u001a\u00020%J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\'"}, d2 = {"Lcom/example/network/MovieResponseDto;", "", "id", "", "title", "overview", "poster_path", "genre_ids", "", "", "vote_average", "", "release_date", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;DLjava/lang/String;)V", "getGenre_ids", "()Ljava/util/List;", "getId", "()Ljava/lang/String;", "getOverview", "getPoster_path", "getRelease_date", "getTitle", "getVote_average", "()D", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toMovie", "Lcom/example/model/Movie;", "toString", "network_release"})
public final class MovieResponseDto {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String overview = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String poster_path = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.Integer> genre_ids = null;
    private final double vote_average = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String release_date = null;
    
    public MovieResponseDto(@com.squareup.moshi.Json(name = "id")
    @org.jetbrains.annotations.NotNull
    java.lang.String id, @com.squareup.moshi.Json(name = "title")
    @org.jetbrains.annotations.NotNull
    java.lang.String title, @com.squareup.moshi.Json(name = "overview")
    @org.jetbrains.annotations.NotNull
    java.lang.String overview, @com.squareup.moshi.Json(name = "poster_path")
    @org.jetbrains.annotations.NotNull
    java.lang.String poster_path, @com.squareup.moshi.Json(name = "genre_ids")
    @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> genre_ids, @com.squareup.moshi.Json(name = "vote_average")
    double vote_average, @com.squareup.moshi.Json(name = "release_date")
    @org.jetbrains.annotations.NotNull
    java.lang.String release_date) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getOverview() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPoster_path() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Integer> getGenre_ids() {
        return null;
    }
    
    public final double getVote_average() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRelease_date() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.model.Movie toMovie() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Integer> component5() {
        return null;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.network.MovieResponseDto copy(@com.squareup.moshi.Json(name = "id")
    @org.jetbrains.annotations.NotNull
    java.lang.String id, @com.squareup.moshi.Json(name = "title")
    @org.jetbrains.annotations.NotNull
    java.lang.String title, @com.squareup.moshi.Json(name = "overview")
    @org.jetbrains.annotations.NotNull
    java.lang.String overview, @com.squareup.moshi.Json(name = "poster_path")
    @org.jetbrains.annotations.NotNull
    java.lang.String poster_path, @com.squareup.moshi.Json(name = "genre_ids")
    @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Integer> genre_ids, @com.squareup.moshi.Json(name = "vote_average")
    double vote_average, @com.squareup.moshi.Json(name = "release_date")
    @org.jetbrains.annotations.NotNull
    java.lang.String release_date) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}