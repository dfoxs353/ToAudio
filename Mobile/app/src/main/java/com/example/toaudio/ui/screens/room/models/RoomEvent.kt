package com.example.toaudio.ui.screens.room.models

sealed class RoomEvent {
    object SendMessageClicked: RoomEvent()
    object AddTrackClicked: RoomEvent()
    object ChoseTrack: RoomEvent()

    data class MessageValueChanged(val value: String): RoomEvent()
}