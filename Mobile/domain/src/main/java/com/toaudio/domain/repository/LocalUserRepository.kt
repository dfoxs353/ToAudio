package com.toaudio.domain.repository

import com.toaudio.domain.models.User

interface LocalUserRepository {

    suspend fun saveUser(user: User)
    suspend fun getUser() : User?

    suspend fun saveJWToken(accessToken: String)

    fun getUserId(): String?

    fun getUserName(): String?

    fun getAccessToken(): String?

    fun clearUserData()
}