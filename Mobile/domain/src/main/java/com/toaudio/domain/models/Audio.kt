package com.toaudio.domain.models


data class Audio(
    val uri: String,
    val displayName: String,
    val id: Long,
    val artist: String,
    val data: String,
    val duration: Int,
    val title: String,
)