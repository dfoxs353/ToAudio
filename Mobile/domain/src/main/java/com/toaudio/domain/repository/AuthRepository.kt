package com.toaudio.domain.repository

import com.toaudio.domain.models.Result
import com.toaudio.domain.models.User

interface AuthRepository {

    suspend fun signin(username: String, password: String): Result<User>

    suspend fun signup(password: String, username: String): Result<User>

    suspend fun refresh(token: String): Result<User>
}