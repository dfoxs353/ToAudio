package com.example.toaudio.data.remote.websocket

import com.example.toaudio.data.models.Message
import com.example.toaudio.player.service.ToAudioState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatWebSocketHandler : WebSocketListener()
{

    private val _chatState: MutableStateFlow<ChatState> =
        MutableStateFlow(ChatState.Success(null))
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        _chatState.value = ChatState.Close(reason)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)

        val message = Json.decodeFromString<Message>(text)
        _chatState.value = ChatState.Success(message = message)

    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }
}

sealed class ChatState{
    data class Success(val message: Message?): ChatState()
    object Error : ChatState()
    data class Close(val reason: String) : ChatState()
}

