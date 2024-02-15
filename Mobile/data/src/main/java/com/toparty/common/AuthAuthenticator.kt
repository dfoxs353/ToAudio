package com.toparty.common

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.toaudio.domain.models.Result
import com.toaudio.domain.models.User
import com.toaudio.domain.repository.LocalUserRepository
import com.toparty.data.remote.auth.AuthApi
import com.toparty.data.remote.auth.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val userRepository: LocalUserRepository,
    private val baseUrl: String,
) : Authenticator {


    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val token = userRepository.getAccessToken()
            when (val newToken = getNewToken()) {
                is Result.Success -> {
                    newToken.data.apply {
                        userRepository.saveJWToken(this.accessToken)
                    }
                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${newToken.data.accessToken}")
                        .build()
                }
                is com.toaudio.domain.models.Result.Error -> {
                    userRepository.clearUserData()
                    null
                }
            }
        }
    }

    private suspend fun getNewToken(): Result<User> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(AuthApi::class.java)
        return try {
            Result.Success(
                withContext(Dispatchers.IO) {
                    service.refresh().await().toUser()
                }
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            com.toaudio.domain.models.Result.Error(IOException("Error refresh", e))
        }
    }
}