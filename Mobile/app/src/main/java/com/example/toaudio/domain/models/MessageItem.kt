package com.example.toaudio.domain.models

import com.example.toaudio.data.models.TextMessage

data class MessageItem(
    val message: TextMessage,
    val isClient: Boolean,
)