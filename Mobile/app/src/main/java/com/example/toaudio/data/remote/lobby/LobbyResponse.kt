package com.example.toaudio.data.remote.lobby

data class LobbyResult(
    val success: LobbyResponse? = null,
    val error: Int? = null
)
data class LobbyResponse(
    val roomId:String,
)
