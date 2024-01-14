package com.example.toaudio.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Message(
    val username: String = "",
    val msg: String = "",
)

fun Message.toJson() : String{
    return Json.encodeToString(Message(username,msg))
}