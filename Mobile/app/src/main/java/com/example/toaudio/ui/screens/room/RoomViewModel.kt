package com.example.toaudio.ui.screens.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toaudio.common.EventHandler
import com.example.toaudio.data.models.ConnectChatRequest
import com.example.toaudio.data.models.TextMessage
import com.example.toaudio.data.models.MessageItem
import com.example.toaudio.data.models.toJson
import com.example.toaudio.data.remote.websocket.ChatState
import com.example.toaudio.data.remote.websocket.ChatWebSocketHandler
import com.example.toaudio.data.remote.websocket.WebSocketManager
import com.example.toaudio.data.repository.LocalUserRepository
import com.example.toaudio.ui.navigation.NavigationArgs
import com.example.toaudio.ui.screens.player.models.MusicPlayerViewState
import com.example.toaudio.ui.screens.room.models.RoomEvent
import com.example.toaudio.ui.screens.room.models.RoomViewState
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
                    ChatState.Error -> TODO()
                    is ChatState.Close -> TODO()
                    is ChatState.Message -> addMessage(chatState.message)
                    is ChatState.MembersList -> setMemberList(chatState.membersList)
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
        val connectMessage = ConnectChatRequest(localUserRepository.getAccessToken()!!).toJson()
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
            val message = TextMessage(userName,_viewState.value!!.messageValue)
            chatWebSocket?.send(message.toJson())

            _viewState.postValue(
                _viewState.value?.copy(
                    messageValue = ""
                )
            )
        }
    }
}