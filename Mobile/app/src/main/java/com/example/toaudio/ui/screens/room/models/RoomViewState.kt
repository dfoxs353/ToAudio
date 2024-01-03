package com.example.toaudio.ui.screens.room.models

enum class RoomSubState{
    OwnerRoom,MemberRoom,
}
data class RoomViewState(
    val roomSubState: RoomSubState = RoomSubState.MemberRoom,
    val roomId: String,
)