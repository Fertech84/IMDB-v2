package com.example.imdb_v2.data.repository

import com.example.imdb_v2.data.local.database.dao.MovieDao
import com.example.imdb_v2.data.local.database.model.MovieEntity
import com.example.imdb_v2.domain.model.Movie

enum class MovieType{
    TOP_RATED,
    POPULAR
}

interface MovieRepository {
    suspend fun getTopRated(): List<Movie>
    suspend fun getPopular(): List<Movie>
    suspend fun saveTopRated(movies :List<Movie>)
    suspend fun savePopular(movies :List<Movie>)
    suspend fun searchMove(name : String) : List<Movie>
    suspend fun getMovie(id : Int) : Movie?
}

class MovieRepositoryImpl  (
    private val movieDao: MovieDao
): MovieRepository {
    override suspend fun getTopRated(): List<Movie> {
        val movies =  movieDao.getAllMovies(type = MovieType.TOP_RATED.name)
            .map { it.toMovie() }
        return movies
    }

    override suspend fun getPopular(): List<Movie> {
        val movies = movieDao.getAllMovies(type = MovieType.POPULAR.name)
            .map { it.toMovie() }
        return movies
    }

    override suspend fun saveTopRated(movies: List<Movie>) {
        movieDao.saveMovies(movies.map { it.toTopRatedMovieEntity() })
    }

    override suspend fun savePopular(movies: List<Movie>) {
        movieDao.saveMovies(movies.map { it.toPopularMovieEntity() })
    }

    override suspend fun searchMove(name: String): List<Movie> {
        val movies = movieDao.searchMovie(name).map { it.toMovie() }
        return movies
    }

    override suspend fun getMovie(id: Int): Movie? {
        val movie = movieDao.getMovie(id).map { it.toMovie() }
        return if (movie.isNotEmpty()) movie.first() else null
    }


}

fun MovieEntity.toMovie() : Movie{
    return Movie(
        id = this.id,
        movieName = this.movieName,
        imageURL = this.imageURL,
        date = this.date,
        rating = this.rating,
        trailerImage = this.trailerImage,
        description = this.description,
    )
}

fun Movie.toTopRatedMovieEntity() : MovieEntity{
    return MovieEntity(
        id = this.id,
        movieName = this.movieName,
        imageURL = this.imageURL,
        date = this.date,
        rating = this.rating,
        trailerImage = this.trailerImage,
        description = this.description,
        type = MovieType.TOP_RATED.name
    )
}

fun Movie.toPopularMovieEntity(): MovieEntity{
    return MovieEntity(
        id = this.id,
        movieName = this.movieName,
        imageURL = this.imageURL,
        date = this.date,
        rating = this.rating,
        trailerImage = this.trailerImage,
        description = this.description,
        type = MovieType.POPULAR.name
    )
}