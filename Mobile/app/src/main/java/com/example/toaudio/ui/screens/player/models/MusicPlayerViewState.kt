package com.example.toaudio.ui.screens.player.models

import android.net.Uri
import androidx.core.net.toUri


enum class MusicPlayerSubState{
    isPlaying, isPause
}

data class MusicPlayerViewState(
    val playerSubState: MusicPlayerSubState = MusicPlayerSubState.isPause,
    val title: String = "",
    val author: String = "",
    val coverUri: Uri = "".toUri(),
    val duration: Long = 0L,
    val progress: Float = 0f,
)