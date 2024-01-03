package com.example.toaudio.ui.screens.room.models

class Message(
    val username: String,
    val msg: String,
) {
    fun toJsonString(): String{
        return "{\"username\": \"${username}\", \"msg\": \"${msg}\"}"
    }
}