package com.example.imdb_v2.util

import com.example.imdb_v2.data.DTO.MovieDTO
import com.example.imdb_v2.data.local.database.model.UserEntity
import com.example.imdb_v2.domain.model.Movie
import com.example.imdb_v2.domain.model.User
import com.example.imdb_v2.view.model.MovieUI
import com.example.imdb_v2.view.model.UserUI

fun Movie.toEntity() : Movie {
    return  Movie(
        id = this.id,
        movieName = this.movieName,
        imageURL = this.imageURL,
        rating = this.rating,
        trailerImage = this.trailerImage,
        date = this.date,
        description = this.description
    )
}

fun Movie.ToMovieUI() : MovieUI {
    return  MovieUI(
        id = this.id,
        movieName = this.movieName,
        imageURL = this.imageURL,
        rating = this.rating,
        trailerImage = this.trailerImage,
        date = this.date,
        description = this.description
    )
}

fun MovieDTO.toUI() : MovieUI {
    return  MovieUI(
        id = this.id,
        movieName = this.movieName,
        imageURL = this.imageURL,
        rating = this.rating,
        trailerImage = this.trailerImage,
        date = this.date,
        description = this.description
    )
}

fun MovieDTO.toMovie() : Movie {
    return  Movie(
        id = this.id,
        movieName = this.movieName,
        imageURL = this.imageURL,
        rating = this.rating,
        trailerImage = this.trailerImage,
        date = this.date,
        description = this.description
    )
}



fun UserUI.toUser() : User{
    return User(
        id = this.uid,
        username = this.username,
        email = this.email,
        password = this.password
    )
}

fun UserEntity.toUserUI(): UserUI{
    return UserUI(
        uid = this.uid,
        username = this.username,
        email = this.email,
        password = this.password
    )
}