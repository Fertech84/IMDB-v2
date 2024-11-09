package com.example.imdb_v2.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("movie")
data class MovieEntity(
    @PrimaryKey val id : Long,
    @ColumnInfo(name = "movie_name") val movieName : String,
    @ColumnInfo(name = "image_url") val imageURL :String?,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "rating") val rating : String,
    @ColumnInfo(name = "trailer_image") val trailerImage : String?,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "type") val type : String
)
