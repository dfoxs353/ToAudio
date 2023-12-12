package com.example.toaudio.data.remote.auth

data class UserRegistrationRequest(
    val password: String,
    val username: String
)

data class UserLoginRequest(
    val username: String,
    val password: String
)