package com.example.imdb_v2.repository

import com.example.imdb_v2.services.MovieService
import com.example.imdb_v2.model.MovieDTO
import com.example.imdb_v2.model.MoviesDTO
import javax.inject.Inject

interface MovieRepository {
    suspend fun getTopRated() : MoviesDTO
    suspend fun getPopular() : MoviesDTO
    suspend fun getMovie(movieId : String) : MovieDTO
}

class MovieRepositoryImpl @Inject constructor (
    private val movieService : MovieService
): MovieRepository{

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