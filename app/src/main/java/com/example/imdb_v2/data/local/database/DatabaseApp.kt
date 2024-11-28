package com.example.imdb_v2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imdb_v2.data.local.database.dao.MovieDao
import com.example.imdb_v2.data.local.database.dao.UserDAO
import com.example.imdb_v2.data.local.database.model.MovieEntity
import com.example.imdb_v2.data.local.database.model.UserEntity

@Database(entities = [UserEntity::class, MovieEntity::class], version = 2, exportSchema = true)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun  getUserDao() : UserDAO
    abstract fun getMovieDao(): MovieDao
}