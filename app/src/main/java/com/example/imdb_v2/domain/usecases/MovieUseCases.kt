package com.example.imdb_v2.domain.usecases

import com.example.imdb_v2.data.network.MovieService
import com.example.imdb_v2.data.repository.MovieRepository
import com.example.imdb_v2.domain.model.Movie
import com.example.imdb_v2.util.toMovie

class MovieUseCases(
    private val movieService: MovieService,
    private val movieRepository: MovieRepository
) {
    suspend fun getTopRated() : List<Movie>{
        var movies : List<Movie>
        try {
            movies =  movieService.getTopRated().results.map { it.toMovie() }
            movieRepository.saveTopRated(movies)
        }catch (error : Exception){
            movies = movieRepository.getTopRated()
        }

        return movies
    }

    suspend fun getPopular() : List<Movie>{
        var movies : List<Movie>
        try {
            movies =  movieService.getPopular().results.map { it.toMovie() }
            movieRepository.savePopular(movies)
        }catch (error : Exception){
            movies = movieRepository.getPopular()
        }

        return movies
    }

    suspend fun getMovie(id : Int) : Movie?{
        var movie : Movie? = null

        movie = try {
            movieService.getMovie(movieId = "$id").toMovie()
        }catch (error : Exception){
            movieRepository.getMovie(id)
        }

        return movie
    }

    suspend fun searchMovie(name : String) : List<Movie>{

        val movies : List<Movie> = try {
            movieService.searchMovies(name).results.map { it.toMovie() }
        }catch (error : Exception){
            movieRepository.searchMove(name)
        }

        return movies
    }



}