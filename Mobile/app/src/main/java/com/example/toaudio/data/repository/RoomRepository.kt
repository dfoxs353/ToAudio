package com.example.toaudio.data.repository

import android.util.Log
import com.example.toaudio.data.remote.room.RoomApi
import com.example.toaudio.data.models.Result
import com.example.toaudio.data.remote.room.RoomResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RoomRepository(
    private val roomDataSource: RoomApi,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun getRoom(): Result<RoomResponse> {
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = roomDataSource.getRoom()
                    response.await()
                }
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(Exception( e.message))
        }
    }

}