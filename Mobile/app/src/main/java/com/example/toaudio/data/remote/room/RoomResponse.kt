package com.example.toaudio.data.remote.room

data class RoomResult(
    val success: RoomResponse? = null,
    val error: Int? = null
)
data class RoomResponse(
    val roomId:String,
)
