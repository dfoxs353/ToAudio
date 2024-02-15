package com.example.toaudio.data.repository

import android.util.Log
import com.example.toaudio.data.remote.auth.AuthApi
import com.example.toaudio.data.remote.auth.AuthResponse
import com.example.toaudio.data.remote.auth.UserLoginRequest
import com.example.toaudio.data.remote.auth.UserRegistrationRequest
import com.example.toaudio.data.remote.auth.toUser
import com.example.toaudio.domain.models.Result
import com.example.toaudio.domain.models.User
import com.example.toaudio.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AuthRepositoryIml(
    private val authDataSource: AuthApi,
    private val ioDispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun signin(username: String, password: String): Result<User> {
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = authDataSource.loginUser(UserLoginRequest(username, password))
                    response.await()
                }.toUser()
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(exception = Exception( e.message))
        }
    }

    override suspend fun signup(password: String, username: String): Result<User> {
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = authDataSource.registerUser(UserRegistrationRequest(password, username))
                    response.await()
                }.toUser()
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(exception = Exception( e.message))
        }

    }

    override suspend fun refresh(token: String): Result<User> {
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = authDataSource.refresh()
                    response.await()
                }.toUser()
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(exception = Exception( e.message))
        }
    }

}