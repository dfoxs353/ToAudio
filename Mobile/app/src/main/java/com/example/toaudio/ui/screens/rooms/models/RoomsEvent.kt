package com.example.toaudio.ui.screens.rooms.models

sealed class RoomsEvent {
    object CreateRoomsClicked : RoomsEvent()
    object EnterRoomByIdClicked : RoomsEvent()
    data class RoomIdChanged(val value: String): RoomsEvent()
}