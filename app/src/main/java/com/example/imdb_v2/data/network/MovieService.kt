package com.example.imdb_v2.data.network

import com.example.imdb_v2.data.DTO.MovieDTO
import com.example.imdb_v2.data.DTO.MoviesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "c5c47722a4adcc77f6e84f28a48b857a"

interface MovieService {
    @GET("movie/top_rated?")
    suspend fun getTopRated(
        @Query("api_key") apikey: String = API_KEY
    ): MoviesDTO

    @GET("movie/popular?")
    suspend fun getPopular(
        @Query("api_key") apikey: String = API_KEY
    ): MoviesDTO

    @GET("movie/{movie_id}?")
    suspend fun getMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apikey: String = API_KEY
    ): MovieDTO

    @GET("search/movie?")
    suspend fun searchMovies(
        @Query("query") movieName: String,
        @Query("api_key") apiKey: String = "c5c47722a4adcc77f6e84f28a48b857a"
    ): MoviesDTO
}


