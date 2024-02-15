package com.toaudio.domain.models


data class TextMessage(
    val username: String = "",
    val msg: String = "",
)

data class MessageItem(
    val message: TextMessage,
    val isClient: Boolean,
)