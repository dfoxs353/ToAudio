package com.toparty.data.remote.room

import com.toaudio.domain.models.Room


data class RoomResponse(
    val roomId:String,
)

internal fun RoomResponse.toRoom() : com.toaudio.domain.models.Room {
    return com.toaudio.domain.models.Room(
        roomId = this.roomId,
    )
}

