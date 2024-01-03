package com.example.toaudio.ui.screens.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toaudio.common.EventHandler
import com.example.toaudio.ui.screens.login.models.LoginEvent
import com.example.toaudio.ui.screens.room.models.RoomEvent
import com.example.toaudio.ui.screens.room.models.RoomViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(

):ViewModel(), EventHandler<RoomEvent> {

    private val _viewState = MutableLiveData(RoomViewState())
    val viewState: LiveData<RoomViewState> = _viewState

    override fun obtainEvent(event: RoomEvent) {
        when(event){
            RoomEvent.AddTrackClicked -> TODO()
            RoomEvent.ChoseTrack -> TODO()
            RoomEvent.SendMessageClicked -> sendMessage()
            is RoomEvent.MessageValueChanged -> messageValueChanged(event.value)
        }
    }

    private fun messageValueChanged(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(
                _viewState.value?.copy(
                    messageValue = value
                )
            )
        }
    }

    private fun sendMessage() {
        TODO("Not yet implemented")
    }
}