package com.example.toaudio.domain.repository

import com.example.toaudio.domain.models.Result
import com.example.toaudio.domain.models.User

interface AuthRepository {

    suspend fun signin(username: String, password: String): Result<User>

    suspend fun signup(password: String, username: String): Result<User>

    suspend fun refresh(token: String): Result<User>
}