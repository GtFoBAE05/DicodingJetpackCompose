package com.example.movieapp.data.remote.retrofit

import com.example.movieapp.data.remote.response.DetailMovieResponse
import com.example.movieapp.data.remote.response.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): PopularMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovies(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): DetailMovieResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") title: String,
        @Query("api_key") apiKey: String,
    ): PopularMoviesResponse

}