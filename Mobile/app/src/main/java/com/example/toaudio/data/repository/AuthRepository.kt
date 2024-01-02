package com.example.toaudio.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.toaudio.data.remote.auth.AuthApi
import com.example.toaudio.data.remote.auth.AuthResponse
import com.example.toaudio.data.remote.auth.UserLoginRequest
import com.example.toaudio.data.remote.auth.UserRegistrationRequest
import com.example.toaudio.data.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class AuthRepository(
    private val authDataSource: AuthApi,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun signin(username: String, password: String): Result<AuthResponse> {
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = authDataSource.loginUser(UserLoginRequest(username, password))
                    response.await()
                }
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(exception = Exception("Error logging in" + e.message.toString()))
        }
    }

    suspend fun signup(password: String, username: String): Result<AuthResponse> {
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = authDataSource.registerUser(UserRegistrationRequest(password, username))
                    response.await()
                }
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(exception = Exception("Error logging in" + e.message.toString()))
        }

    }

    suspend fun refresh(token: String): Result<AuthResponse>{
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = authDataSource.refresh()
                    response.await()
                }
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(exception = Exception("Error refresh" + e.message.toString()))
        }
    }

}