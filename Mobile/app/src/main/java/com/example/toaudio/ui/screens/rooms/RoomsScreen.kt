package com.example.toaudio.ui.screens.rooms



import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.toaudio.R
import com.example.toaudio.domain.models.Result
import com.example.toaudio.ui.navigation.NavigationTree
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

    val context = LocalContext.current
    val errorText = stringResource(id = R.string.error_room_connect)

    LaunchedEffect(roomsViewModel) {
        roomsViewModel.resultChannel.collect{result ->
            when(result){
                is Result.Error -> {
                    Toast.makeText(
                        context,
                        "$errorText",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Result.Success -> {
                    navController.navigate(NavigationTree.Room.route + "/${result.data.roomId}")
                }
            }
        }
    }

    Surface(
        modifier = modifier,
    ) {
        with(viewState.value!!){
            when(roomsViewSubState){
                RoomsSubState.CustomRooms -> RoomsView(
                    roomIdValue = roomIdValue,
                    onRoomIdFieldChanged = {roomsViewModel.obtainEvent(RoomsEvent.RoomIdChanged(it))},
                    enterRoomClick = { navController.navigate(NavigationTree.Room.route + "/${roomIdValue}")},
                    createRoomClick = {roomsViewModel.obtainEvent(RoomsEvent.CreateRoomsClicked)},
                )
            }
        }
    }
}