package com.example.toaudio.ui.screens.rooms


import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun RoomsScreen(
    modifier: Modifier = Modifier,
    roomsViewModel: RoomsViewModel ,
    navController: NavController,
){
    val state = roomsViewModel

    Surface(
        modifier = modifier,
    ) {

    }
}