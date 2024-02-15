package com.toparty.data.repository

import android.util.Log
import com.toparty.data.remote.room.RoomApi
import com.toaudio.domain.models.Result
import com.toparty.data.remote.room.RoomResponse
import com.toparty.data.remote.room.toRoom
import com.toaudio.domain.models.Room
import com.toaudio.domain.repository.RoomRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
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