package com.example.toaudio.ui.screens.player.models

import android.net.Uri
import androidx.core.net.toUri
import com.example.toaudio.player.service.ToAudioState


sealed class MusicPlayerSubState {
    object Initial : MusicPlayerSubState()
    object Ready : MusicPlayerSubState()
}

data class MusicPlayerViewState(
    var playerSubState: MusicPlayerSubState = MusicPlayerSubState.Initial,
    var isPlaying: Boolean = false,
    var title: String = "",
    var author: String = "",
    var coverUri: Uri = "".toUri(),
    var duration: Long = 0L,
    var progress: Float = 0f,
    var progressString: String = "00:00",
)