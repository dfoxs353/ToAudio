package com.example.toaudio.ui.screens.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toaudio.common.EventHandler
import com.toaudio.domain.models.Result
import com.toaudio.domain.models.Room
import com.example.toaudio.ui.screens.rooms.models.RoomsEvent
import com.example.toaudio.ui.screens.rooms.models.RoomsViewState
import com.toaudio.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
) : ViewModel(), EventHandler<RoomsEvent> {

    private val _viewState = MutableLiveData(RoomsViewState())
    val viewState: LiveData<RoomsViewState> = _viewState

    private val _resultChannel = Channel<Result<Room>>()
    val resultChannel = _resultChannel.receiveAsFlow()
    override fun obtainEvent(event: RoomsEvent) {
        when (event) {
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
        viewModelScope.launch(Dispatchers.IO) {
            _resultChannel.send(
                Result.Success(
                    Room(
                        roomId = _viewState.value!!.roomIdValue
                    )
                )
            )
        }
    }

    private fun createRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = roomRepository.getRoom()

            _resultChannel.send(result)
        }
    }
}