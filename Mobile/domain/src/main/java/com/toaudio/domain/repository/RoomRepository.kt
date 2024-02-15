package com.toaudio.domain.repository

import com.toaudio.domain.models.Result
import com.toaudio.domain.models.Room

interface RoomRepository {

    suspend fun getRoom(): Result<Room>

}