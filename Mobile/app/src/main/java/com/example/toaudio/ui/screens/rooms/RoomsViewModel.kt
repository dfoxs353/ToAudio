package com.example.toaudio.ui.screens.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toaudio.common.EventHandler
import com.example.toaudio.data.repository.RoomRepository
import com.example.toaudio.ui.screens.rooms.models.RoomsEvent
import com.example.toaudio.ui.screens.rooms.models.RoomsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
) : ViewModel(), EventHandler<RoomsEvent> {

    private val _viewState =  MutableLiveData<RoomsViewState>()
    val viewState: LiveData<RoomsViewState> = _viewState
    override fun obtainEvent(event: RoomsEvent) {
        when(event){
            RoomsEvent.CreateRoomsClicked -> createRoom()
            RoomsEvent.EnterRoomByIdClicked -> enterRoomById()
            is RoomsEvent.RoomIdChanged -> roomIdChanged(event.value)
        }
    }

    private fun roomIdChanged(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(
                _viewState.value?.copy(roomIdValue = value)
            )
        }
    }

    private fun enterRoomById() {
        TODO("Not yet implemented")
    }

    private fun createRoom() {
        TODO("Not yet implemented")
    }
}