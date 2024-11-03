package com.example.imdb_v2.data.DTO

import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    val page : Int,
    val results: List<MovieDTO>
)

data  class MovieDTO(
    val id : Long,

    @SerializedName("original_title")
    val movieName : String,

    @SerializedName("poster_path")
    val imageURL : String,

    @SerializedName("release_date")
    val date : String,

    @SerializedName("vote_average")
    val rating : String,

    @SerializedName("backdrop_path")
    val trailerImage : String,

    @SerializedName("overview")
    val description : String
)
