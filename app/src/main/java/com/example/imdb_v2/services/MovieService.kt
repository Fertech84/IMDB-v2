package com.example.imdb_v2.services

import com.example.imdb_v2.model.MovieDTO
import com.example.imdb_v2.model.MoviesDTO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieServiceConfig {



    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit : Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}


interface MovieService {


    @GET("movie/top_rated?api_key=c5c47722a4adcc77f6e84f28a48b857a")
    suspend fun getTopRated(): MoviesDTO

    @GET("movie/popular?api_key=c5c47722a4adcc77f6e84f28a48b857a")
    suspend fun getPopular() : MoviesDTO

    @GET("movie/{movie_id}?api_key=c5c47722a4adcc77f6e84f28a48b857a")
    suspend fun getMovie(@Path("movie_id") movieId: String) : MovieDTO

    @GET("search/movie?")
    suspend fun searchMovies(
        @Query("query") movieName : String,
        @Query("api_key") apiKey : String = "c5c47722a4adcc77f6e84f28a48b857a"
    ): MoviesDTO
}