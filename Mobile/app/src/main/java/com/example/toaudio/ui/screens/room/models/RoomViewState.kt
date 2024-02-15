package com.example.toaudio.ui.screens.room.models

import com.example.toaudio.domain.models.MessageItem

enum class RoomSubState{
    OwnerRoom,MemberRoom,TrackList
}
data class RoomViewState(
    val roomSubState: RoomSubState = RoomSubState.MemberRoom,
    val roomId: String = "",
    val memberList: List<String> = listOf(),
    val messageList: List<MessageItem> = listOf(),
    val messageValue: String = "",
)