package com.example.toaudio.data.repository

import android.util.Log
import com.example.toaudio.data.remote.room.RoomApi
import com.example.toaudio.domain.models.Result
import com.example.toaudio.data.remote.room.RoomResponse
import com.example.toaudio.data.remote.room.toRoom
import com.example.toaudio.domain.models.Room
import com.example.toaudio.domain.repository.RoomRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RoomRepositoryImpl(
    private val roomDataSource: RoomApi,
    private val ioDispatcher: CoroutineDispatcher,
) : RoomRepository {

    override suspend fun getRoom(): Result<Room> {
        return try {
            Result.Success(
                withContext(ioDispatcher) {
                    val response = roomDataSource.getRoom()
                    response.await()
                }.toRoom()
            )
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            Result.Error(Exception( e.message))
        }
    }

}