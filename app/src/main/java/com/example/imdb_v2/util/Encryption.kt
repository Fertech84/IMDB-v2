package com.example.imdb_v2.util

import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


private const val HASH_KEY = "gH3m/VAaJIdWtRQMtGwi9EogXtOVrWaYF48BQNYs1sSihdMbI0/QQxZpHnKX37gm"

private fun generateAESKey(keySize: Int = 256) : SecretKey{
    val keygen : KeyGenerator = KeyGenerator.getInstance("AES")
    keygen.init(keySize)
    return keygen.generateKey()
}

fun cipherPassword(password : String) =  password

fun comparePassword(hashedPassword : String, password: String ) = hashedPassword == password