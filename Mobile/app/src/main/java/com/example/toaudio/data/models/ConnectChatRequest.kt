package com.example.toaudio.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ConnectChatRequest(
    @SerialName("access_token")
    val accessToken: String,
)


fun ConnectChatRequest.toJson() : String{
    return Json.encodeToString(this)
}