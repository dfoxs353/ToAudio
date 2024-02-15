package com.example.toaudio.data.remote.room

import com.example.toaudio.domain.models.Room


data class RoomResponse(
    val roomId:String,
)

internal fun RoomResponse.toRoom() : Room {
    return Room(
        roomId = this.roomId,
    )
}

