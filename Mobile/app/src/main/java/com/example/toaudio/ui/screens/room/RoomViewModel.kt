package com.example.toaudio.ui.screens.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toaudio.common.EventHandler
import com.toaudio.domain.models.MessageItem
import com.toparty.data.models.toJson
import com.example.toaudio.ui.navigation.NavigationArgs
import com.example.toaudio.ui.screens.room.models.RoomEvent
import com.example.toaudio.ui.screens.room.models.RoomViewState
import com.toaudio.domain.models.TextMessage
import com.toaudio.domain.repository.LocalUserRepository
import com.toparty.data.remote.websocket.ChatWebSocketHandler
import com.toparty.data.remote.websocket.WebSocketManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.WebSocket
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val webSocketManager: WebSocketManager,
    private val savedStateHandle: SavedStateHandle,
    private val localUserRepository: LocalUserRepository,
) : ViewModel(), EventHandler<RoomEvent> {

    private val _viewState = MutableLiveData(RoomViewState())
    val viewState: LiveData<RoomViewState> = _viewState

    private var chatWebSocket: WebSocket? = null
    private val chatWebSocketHandle = ChatWebSocketHandler()

    private var userName = ""

    init {
        savedStateHandle.get<String>(NavigationArgs.RoomId.name)?.let { roomId ->
            viewModelScope.launch {
                _viewState.postValue(
                    _viewState.value?.copy(
                        roomId = roomId
                    )
                )
            }

            Log.d("WEBSOCKET", roomId)
            initChat(roomId)
        }

        userName = localUserRepository.getUserName()!!
        Log.d("WEBSOCKET", userName)
    }

    init {
        viewModelScope.launch {
            chatWebSocketHandle.chatState.collectLatest { chatState ->
                when (chatState) {
                    com.toparty.data.remote.websocket.ChatState.Error -> TODO()
                    is com.toparty.data.remote.websocket.ChatState.Close -> TODO()
                    is com.toparty.data.remote.websocket.ChatState.Message -> addMessage(chatState.message)
                    is com.toparty.data.remote.websocket.ChatState.MembersList -> setMemberList(chatState.membersList)
                }
            }
        }
    }

    private fun setMemberList(members: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(
                _viewState.value?.copy(
                    memberList = members,
                )
            )
        }
    }

    private fun initChat(roomId: String) {
        chatWebSocket = webSocketManager.createChatWebSocket(roomId, chatWebSocketHandle)
        val connectMessage = com.toparty.data.models.ConnectChatRequest(localUserRepository.getAccessToken()!!)
            .toJson()
        Log.d("WEBSOCKET", connectMessage)

        chatWebSocket?.send(connectMessage)
    }


    private fun addMessage(message: TextMessage?) {
        viewModelScope.launch(Dispatchers.IO) {
            message?.let {
                val updatedMessageList = _viewState.value!!.messageList.toMutableList()
                val userName = localUserRepository.getUserName()!!
                val isClient = userName == message.username

                updatedMessageList.add(
                    MessageItem(
                        message = message,
                        isClient = isClient,
                    )
                )

                _viewState.postValue(
                    _viewState.value!!.copy(
                        messageList = updatedMessageList
                    )
                )
            }
        }
    }



    override fun obtainEvent(event: RoomEvent) {
        when (event) {
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
        viewModelScope.launch(Dispatchers.IO){
            val message =
                com.toparty.data.models.TextMessage(userName, _viewState.value!!.messageValue)
            chatWebSocket?.send(message.toJson())

            _viewState.postValue(
                _viewState.value?.copy(
                    messageValue = ""
                )
            )
        }
    }
}