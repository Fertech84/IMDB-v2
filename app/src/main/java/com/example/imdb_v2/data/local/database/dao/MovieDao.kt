package com.example.imdb_v2.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdb_v2.data.local.database.model.MovieEntity
import com.example.imdb_v2.domain.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE type = :type ")
    fun getAllMovies(type : String) : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies : List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id : Int) : List<MovieEntity>

    @Query("SELECT * FROM movie WHERE movie_name like '%' || :name || '%'")
    fun searchMovie(name : String) : List<MovieEntity>
}