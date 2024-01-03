package com.example.toaudio.ui.screens.rooms



import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.toaudio.ui.screens.rooms.models.RoomsEvent
import com.example.toaudio.ui.screens.rooms.models.RoomsSubState
import com.example.toaudio.ui.screens.rooms.views.RoomsView

@Composable
fun RoomsScreen(
    modifier: Modifier = Modifier,
    roomsViewModel: RoomsViewModel ,
    navController: NavController,
){
    val viewState = roomsViewModel.viewState.observeAsState()

    Surface(
        modifier = modifier,
    ) {
        with(viewState.value!!){
            when(roomsViewSubState){
                RoomsSubState.CustomRooms -> RoomsView(
                    roomIdValue = roomIdValue,
                    onRoomIdFieldChanged = {roomsViewModel.obtainEvent(RoomsEvent.RoomIdChanged(it))},
                    enterRoomClick = { roomsViewModel.obtainEvent(RoomsEvent.EnterRoomByIdClicked)},
                    createRoomClick = {roomsViewModel.obtainEvent(RoomsEvent.CreateRoomsClicked)},
                )
            }
        }
    }
}