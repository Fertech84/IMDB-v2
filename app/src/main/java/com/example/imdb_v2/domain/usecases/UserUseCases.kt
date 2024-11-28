package com.example.imdb_v2.domain.usecases

import android.util.Log
import com.example.imdb_v2.data.local.database.model.UserEntity
import com.example.imdb_v2.data.repository.UserRepository
import com.example.imdb_v2.domain.model.User
import com.example.imdb_v2.util.cipherPassword
import com.example.imdb_v2.util.toEntity
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class UserUseCases(private val userRepository: UserRepository){


    suspend fun signup(user : User) : Boolean{

        val foundUSer = userRepository.findUserByEmail(user.email)

        if (foundUSer == null) {

            userRepository.createUser(user.toEntity())

            Log.e(">>>>", userRepository.getAllUsers().toString())
            return true
        }
        return false
    }

    suspend fun login(email : String, password : String) : UserEntity?{
        val foundUser : UserEntity? = userRepository.findUserByEmail(email)
        if (foundUser != null) {
            return if (foundUser.password == password) foundUser else null
        }
        return null
    }

    private fun User.toEntity() : UserEntity {
        return UserEntity(
            uid = this.id,
            username = this.username,
            email = this.email,
            password = cipherPassword(this.password)
        )
    }





}




