package com.example.toaudio.ui.screens.player

import androidx.lifecycle.ViewModel
import com.example.toaudio.common.EventHandler
import com.example.toaudio.ui.screens.player.models.MusicPlayerEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(

) : ViewModel(), EventHandler<MusicPlayerEvent>
{
    override fun obtainEvent(event: MusicPlayerEvent) {
        TODO("Not yet implemented")
    }
}