package com.example.toaudio.data.repository

import android.util.Log
import com.example.toaudio.data.remote.lobby.LobbyApi
import com.example.toaudio.data.remote.lobby.LobbyResponse
import com.example.toaudio.data.models.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LobbyRepository(
    private val lobbyDataSource: LobbyApi,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun getRoom(): Result<LobbyResponse> {
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = lobbyDataSource.getRoom()
                    response.await()
                }
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(Exception( e.message))
        }
    }

}