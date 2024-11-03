package com.example.imdb_v2.view.model.mapper

import com.example.imdb_v2.data.DTO.MovieDTO
import com.example.imdb_v2.domain.model.Movie
import com.example.imdb_v2.view.model.MovieUI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

class MovieToMovieUIMapper {

    fun movieToMovieUI(movie: Movie): MovieUI{
        val newMovieUI = MovieUI(
            movieName = movie.movieName,
            trailerImage = movie.trailerImage,
            imageURL = movie.imageURL,
            date = movie.date,
            rating = movie.rating,
            description = movie.description,
            id = movie.id
        )

        return newMovieUI
    }


    fun movieUIToMovie(movieUI : MovieUI) : Movie {
        val newMovie = Movie(
            movieName = movieUI.movieName,
            trailerImage = movieUI.trailerImage,
            date = movieUI.date,
            imageURL = movieUI.imageURL,
            rating = movieUI.rating,
            description = movieUI.description,
            id = movieUI.id
        )
        return newMovie
    }
}

