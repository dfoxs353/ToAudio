package com.example.toaudio.ui.screens.room

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.toaudio.R
import com.example.toaudio.ui.screens.room.models.RoomEvent
import com.example.toaudio.ui.screens.room.views.ChatView
import com.example.toaudio.ui.screens.room.views.RoomView

@Composable
fun RoomScreen(
    modifier: Modifier  = Modifier,
    roomViewModel: RoomViewModel,
    navController: NavController,
){
    val viewState = roomViewModel.viewState.observeAsState()
    val musicPlayerViewState = roomViewModel.musicPlayerState.observeAsState()



    Surface(
        modifier = modifier
    ) {
        with(viewState.value!!){
            RoomView(
                messageList = messageList,
                messageValue = messageValue,
                membersList = memberList,
                onMessageValueChanged = { roomViewModel.obtainEvent(RoomEvent.MessageValueChanged(it)) },
                sendMessageClick = { roomViewModel.obtainEvent(RoomEvent.SendMessageClicked) },
                musicPlayerViewState = musicPlayerViewState.value!!,
                roomId = roomId,
            )
        }
    }
    
}