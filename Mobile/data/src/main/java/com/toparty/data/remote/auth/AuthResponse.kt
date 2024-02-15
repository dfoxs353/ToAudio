package com.toparty.data.remote.auth

import com.toaudio.domain.models.User

data class AuthResponse(
    val access_token: String,
    val user: UserResponse
)

data class UserResponse(
    val id: String,
    val username: String,
    val password: String,
)

internal fun AuthResponse.toUser() : com.toaudio.domain.models.User {
    return com.toaudio.domain.models.User(
        userid = this.user.id,
        userName = this.user.username,
        accessToken = this.access_token,
    )
}