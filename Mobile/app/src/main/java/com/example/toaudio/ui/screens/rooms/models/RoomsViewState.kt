package com.example.toaudio.ui.screens.rooms.models

enum class RoomsSubState{
    CustomRooms
}

data class RoomsViewState(
    val roomsViewSubState: RoomsSubState  = RoomsSubState.CustomRooms,
    val roomIdValue: String = "",
)