package com.example.toaudio.di

import android.content.Context
import android.content.SharedPreferences
import com.example.toaudio.common.AuthAuthenticator
import com.example.toaudio.common.AuthInterceptor
import com.example.toaudio.data.remote.auth.AuthApi
import com.example.toaudio.data.remote.room.RoomApi
import com.example.toaudio.data.remote.websocket.WebSocketManager
import com.example.toaudio.data.repository.AuthRepository
import com.example.toaudio.data.repository.RoomRepository
import com.example.toaudio.data.repository.LocalUserRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    private companion object{
        val baseUrl = "http://10.0.2.2:80/"
    }
    @Provides
    @Singleton
    fun provideLocalUserRepository(sharedPreferences: SharedPreferences): LocalUserRepository {
        return LocalUserRepository(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        val PREFS_NAME = "user_prefs"
        return context.getSharedPreferences(context.packageName + PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: LocalUserRepository): AuthInterceptor =
        AuthInterceptor(tokenManager)

    @Singleton
    @Provides
    fun provideAuthAuthenticator(tokenManager: LocalUserRepository): AuthAuthenticator =
        AuthAuthenticator(tokenManager,baseUrl)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun provideAuthAPIService(retrofit: Retrofit.Builder): AuthApi =
        retrofit
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(AuthApi::class.java)

    @Singleton
    @Provides
    fun provideRoomAPIService(retrofit: Builder, okHttpClient: OkHttpClient): RoomApi =
        retrofit
            .client(okHttpClient)
            .build()
            .create(RoomApi::class.java)


    @Singleton
    @Provides
    fun provideRoomRepository(roomApi: RoomApi): RoomRepository {
        return RoomRepository(roomApi, Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun provideWebsocketManager(): WebSocketManager {
        return WebSocketManager(
            OkHttpClient.Builder()
                .build()
        )
    }

    @Provides
    @Singleton
    fun provideRemoteAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepository(authApi, Dispatchers.IO)
    }
}