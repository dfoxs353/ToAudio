package com.example.toaudio.data.remote.websocket

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

class WebSocketManager @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val baseUrl: String,
){



    fun createChatWebSocket(socketId: String, listener: WebSocketListener): WebSocket {
        val url = "${baseUrl}ws/$socketId/${WebSocketRoot.Chat.route}"
        Log.d("WEBSOCKET", url)
        val request = Request.Builder().url(url).build()
        return okHttpClient.newWebSocket(request, listener)
    }


    fun createFileWebSocket(socketId: String, listener: WebSocketListener): WebSocket {
        val url = "$baseUrl$socketId/${WebSocketRoot.File.route}"
        Log.d("WEBSOCKET", url)
        val request = Request.Builder().url(url).build()
        return okHttpClient.newWebSocket(request, listener)
    }

}

sealed class WebSocketRoot(val route: String){
    object Chat : WebSocketRoot(route = "chat")
    object File : WebSocketRoot(route = "file")
}