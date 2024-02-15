package com.example.toaudio.data.remote.auth

import com.example.toaudio.domain.models.User

data class AuthResponse(
    val access_token: String,
    val user: UserResponse
)

data class UserResponse(
    val id: String,
    val username: String,
    val password: String,
)

internal fun AuthResponse.toUser() : User{
    return User(
        userid = this.user.id,
        userName = this.user.username,
        accessToken = this.access_token,
    )
}