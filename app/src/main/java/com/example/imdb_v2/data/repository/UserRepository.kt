package com.example.imdb_v2.data.repository

import android.util.Log
import com.example.imdb_v2.data.local.database.dao.UserDAO
import com.example.imdb_v2.data.local.database.model.UserEntity
import javax.inject.Inject

interface UserRepository {
    suspend fun createUser(userEntity : UserEntity)
    suspend fun findUserByUsername(username : String) : UserEntity?
    suspend fun findUserByEmail(email : String) : UserEntity?
    suspend fun getAllUsers(): List<UserEntity>
}

class UserRepositoryImpl  (
    private val userDAO: UserDAO
) : UserRepository {



    override suspend fun createUser(userEntity: UserEntity) {
        userDAO.createNewUser(userEntity)
    }

    override suspend fun findUserByUsername(username: String) : UserEntity? {
        val foundUsers : List<UserEntity> = userDAO.findUserByName(username)
        return if (foundUsers.isNotEmpty()) foundUsers.first()
        else null
    }

    override suspend fun findUserByEmail(email: String): UserEntity? {
        val foundUsers : List<UserEntity> = userDAO.findUserByEmail(email)
        return if (foundUsers.isNotEmpty()) foundUsers.first()
        else null
    }

    override suspend fun getAllUsers(): List<UserEntity> {
        return userDAO.getAllUsers()
    }

}