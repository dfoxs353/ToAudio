package com.example.toaudio.ui.navigation

sealed class NavigationTree( val route: String) {
    //Splash, Login, Rooms, Room,
    object Splash : NavigationTree("splash")
    object Login : NavigationTree("login")
    object Rooms : NavigationTree("rooms")
    object Room : NavigationTree("room")
}