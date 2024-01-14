package com.example.toaudio.ui.screens.player.models

sealed class MusicPlayerEvent{
    object PlayPause : MusicPlayerEvent()
    data class SeekTo(val position: Float) : MusicPlayerEvent()
    object SeekToNext : MusicPlayerEvent()
    object Backward : MusicPlayerEvent()
    object Forward : MusicPlayerEvent()
    data class UpdateProgress(val newProgress: Float) : MusicPlayerEvent()
}