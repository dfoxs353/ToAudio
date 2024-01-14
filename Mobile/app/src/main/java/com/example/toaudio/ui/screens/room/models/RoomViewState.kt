package com.example.toaudio.ui.screens.room.models

import com.example.toaudio.data.models.Member
import com.example.toaudio.data.models.MessageItem

enum class RoomSubState{
    OwnerRoom,MemberRoom,TrackList
}
data class RoomViewState(
    val roomSubState: RoomSubState = RoomSubState.MemberRoom,
    val roomId: String = "",
    val memberList: List<Member> = listOf(),
    val messageList: List<MessageItem> = listOf(),
    val messageValue: String = "",
)