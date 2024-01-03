package com.example.toaudio.ui.screens.rooms.models

enum class RoomsSubState{
    CustomRooms
}

data class RoomsViewState(
    var roomsViewSubState: RoomsSubState,
    var roomIdValue: String,
)