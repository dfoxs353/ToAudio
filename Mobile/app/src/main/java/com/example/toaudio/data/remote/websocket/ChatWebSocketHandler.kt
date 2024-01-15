package com.example.toaudio.data.remote.websocket

import android.util.Log
import com.example.toaudio.data.models.TextMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatWebSocketHandler : WebSocketListener()
{

    private val _chatState: MutableStateFlow<ChatState> =
        MutableStateFlow(ChatState.Message(null))
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        _chatState.value = ChatState.Close(reason)
        Log.d("WEBSOCKET", "close reason $reason")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)

        try {
            val message = Json.decodeFromString<TextMessage>(text)
            _chatState.value = ChatState.Message(message = message)

            Log.d("WEBSOCKET", "text message: $message")
        }
        catch (e : SerializationException){
            try {
                val membersList = Json.decodeFromString<List<String>>(text)
                _chatState.value = ChatState.MembersList( membersList = membersList)

                Log.d("WEBSOCKET", "member list: $membersList")
            }
            catch (e: SerializationException){
                Log.e("WEBSOCKET", "error: $e")
            }
        }
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.d("WBSOCKET", "open")
    }
}

sealed class ChatState{
    data class Message(val message: TextMessage?): ChatState()
    data class MembersList(val membersList: List<String>): ChatState()
    object Error : ChatState()
    data class Close(val reason: String) : ChatState()
}

