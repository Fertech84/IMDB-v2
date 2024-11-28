package com.example.imdb_v2.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val uid : Long,
    @ColumnInfo(name = "username") val username : String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "password") val password : String
)
