package com.example.imdb_v2.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.imdb_v2.data.local.database.model.UserEntity

@Dao
interface UserDAO {

    @Query("SELECT * FROM user where username = :username")
    fun findUserByName(username : String) : List<UserEntity>


    @Insert
    fun createNewUser(userEntity : UserEntity)

    @Query("SELECT * FROM user WHERE email = :email")
    fun findUserByEmail(email : String) : List<UserEntity>

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<UserEntity>
}