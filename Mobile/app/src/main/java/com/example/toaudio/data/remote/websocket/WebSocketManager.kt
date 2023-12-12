package com.example.toaudio.data.remote.websocket

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class WebSocketManager(
    private val okHttpClient: OkHttpClient,
){

    fun createWebSocket(baseUrl: String, endpoint: String): WebSocket {
        val url = "$baseUrl/$endpoint"
        Log.d("WEBSOCKET", url)
        val request = Request.Builder().url(url).build()
        return okHttpClient.newWebSocket(request, AppWebSocketListener(endpoint))
    }

    fun createChatWebSocket(baseUrl: String, endpoint: String, messageHandler: (String) -> Unit): WebSocket {
        val url = "$baseUrl/$endpoint"
        Log.d("WEBSOCKET", url)
        val request = Request.Builder().url(url).build()
        return okHttpClient.newWebSocket(request, ChatWebSocketListener(endpoint, messageHandler))
    }


    fun createFileWebSocket(baseUrl: String, endpoint: String): WebSocket {
        val url = "$baseUrl/$endpoint"
        Log.d("WEBSOCKET", url)
        val request = Request.Builder().url(url).build()
        return okHttpClient.newWebSocket(request, AppWebSocketListener(endpoint))
    }

}