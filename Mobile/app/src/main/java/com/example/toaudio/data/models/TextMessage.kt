package com.example.toaudio.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class TextMessage(
    val username: String = "",
    val msg: String = "",
)

fun TextMessage.toJson() : String{
    return Json.encodeToString(TextMessage(username,msg))
}