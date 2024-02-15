package com.toparty.data.repository

import android.util.Log
import com.toparty.data.remote.auth.AuthApi
import com.toparty.data.remote.auth.UserLoginRequest
import com.toparty.data.remote.auth.UserRegistrationRequest
import com.toparty.data.remote.auth.toUser
import com.toaudio.domain.models.Result
import com.toaudio.domain.models.User
import com.toaudio.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryIml @Inject constructor(
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