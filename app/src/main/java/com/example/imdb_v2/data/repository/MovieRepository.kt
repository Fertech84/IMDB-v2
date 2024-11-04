package com.example.imdb_v2.data.repository

import com.example.imdb_v2.data.DTO.MovieDTO
import com.example.imdb_v2.data.DTO.MoviesDTO
import com.example.imdb_v2.domain.model.Movie
import com.example.imdb_v2.domain.usecases.MovieService
import javax.inject.Inject

interface MovieRepository {
    suspend fun getTopRated() : List<Movie>
    suspend fun getPopular() : List<Movie>
    suspend fun getMovie(movieId : String) : Movie
    suspend fun searchMovie(movieName : String) : List<Movie>
}

class MovieRepositoryImpl @Inject constructor (
    private val movieService : MovieService
): MovieRepository {

    override suspend fun getTopRated() : List<Movie> {
        return movieService.getTopRated().results.map {
            movieDTOtoEntity(it)
        }
    }

    override suspend fun getPopular() : List<Movie> {
        return movieService.getPopular().results.map {
            movieDTOtoEntity(it)
        }
    }

    override suspend fun getMovie(movieId: String): Movie {
        return movieDTOtoEntity(movieService.getMovie(movieId))
    }

    override suspend fun searchMovie(movieName: String): List<Movie> {
        return movieService.searchMovies(movieName).results.map {
            movieDTOtoEntity(it)
        }
    }


    private fun movieDTOtoEntity(movieDTO :  MovieDTO) : Movie{
        val mappedMovie = Movie(
            id = movieDTO.id,
            movieName = movieDTO.movieName,
            imageURL = movieDTO.imageURL,
            rating = movieDTO.rating,
            trailerImage = movieDTO.trailerImage,
            date = movieDTO.date,
            description = movieDTO.description
        )

        return mappedMovie
    }


}