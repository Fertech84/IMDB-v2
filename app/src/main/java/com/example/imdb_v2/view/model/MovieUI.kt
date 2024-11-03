package com.example.imdb_v2.view.model

data class MovieUI(
    val id : Long,
    val movieName: String,
    val imageURL : String?,
    val date : String,
    val rating : String,
    val trailerImage : String?,
    val description: String
)
