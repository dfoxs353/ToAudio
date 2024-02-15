package com.example.toaudio.domain.repository

import com.example.toaudio.domain.models.Result
import com.example.toaudio.domain.models.Room

interface RoomRepository {

    suspend fun getRoom(): Result<Room>

}