package com.example.toaudio.ui.screens.room

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldDefaults
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.example.toaudio.player.service.PlayerEvent
import com.example.toaudio.ui.screens.player.MusicPlayerViewModel
import com.example.toaudio.ui.screens.player.models.MusicPlayerEvent
import com.example.toaudio.ui.screens.player.views.MusicPlayerView
import com.example.toaudio.ui.screens.room.models.RoomEvent
import com.example.toaudio.ui.screens.room.views.RoomView

@UnstableApi @OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoomScreen(
    modifier: Modifier = Modifier,
    roomViewModel: RoomViewModel,
    playerViewModel: MusicPlayerViewModel,
    navController: NavController,
) {
    val viewState = roomViewModel.viewState.observeAsState()
    val playerState = playerViewModel.uiState.observeAsState()


    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()

    Surface(
        modifier = modifier
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = BottomSheetScaffoldDefaults.SheetPeekHeight,
            sheetContent = {

                with(playerState.value!!){
                    MusicPlayerView(
                        sheetState = sheetState,
                        scaffoldState = scaffoldState,
                        scope = scope,
                        onPlayOrPauseClick = {playerViewModel.obtainEvent(MusicPlayerEvent.PlayPause)},
                        onForwardClick = { /*TODO*/ },
                        onBackwardClick = { /*TODO*/ },
                        title = title,
                        isPlaying = isPlaying,
                        author = author,
                        coverUri = coverUri,
                    )
                }
            },
        ) {
            with(viewState.value!!) {
                RoomView(
                    messageList = messageList,
                    messageValue = messageValue,
                    membersList = memberList,
                    onMessageValueChanged = { roomViewModel.obtainEvent(RoomEvent.MessageValueChanged(it)) },
                    sendMessageClick = { roomViewModel.obtainEvent(RoomEvent.SendMessageClicked) },
                    roomId = roomId,
                )
            }
        }
    }

}