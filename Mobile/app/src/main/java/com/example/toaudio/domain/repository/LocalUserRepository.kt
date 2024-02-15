package com.example.toaudio.domain.repository

import com.example.toaudio.domain.models.User

interface LocalUserRepository {

    suspend fun saveUser(user: User)
    suspend fun getUser() : User?

    suspend fun saveJWToken(accessToken: String)

    fun getUserId(): String?

    fun getUserName(): String?

    fun getAccessToken(): String?

    fun clearUserData()
}