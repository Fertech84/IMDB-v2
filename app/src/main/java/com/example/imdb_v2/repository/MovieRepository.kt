package com.example.imdb_v2.repository

import MovieService
import MovieServiceConfig
import com.example.imdb_v2.model.MovieDTO
import com.example.imdb_v2.model.MoviesDTO

interface MovieRepository {
    suspend fun getTopRated() : MoviesDTO
    suspend fun getPopular() : MoviesDTO
    suspend fun getMovie(movieId : String) : MovieDTO
}

class MovieRepositoryImpl(private val movieService : MovieService = MovieServiceConfig.movieService): MovieRepository{

    override suspend fun getTopRated() : MoviesDTO {
        return movieService.getTopRated()
    }

    override suspend fun getPopular() : MoviesDTO {
        return movieService.getPopular()
    }

    override suspend fun getMovie(movieId: String): MovieDTO {
        return movieService.getMovie(movieId)
    }


}