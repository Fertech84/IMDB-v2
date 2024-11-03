package com.example.imdb_v2.domain.model

data class Movie(
    val id : Long,
    val movieName: String,
    val imageURL : String?,
    val date : String,
    val rating : String,
    val trailerImage : String?,
    val description: String
)
