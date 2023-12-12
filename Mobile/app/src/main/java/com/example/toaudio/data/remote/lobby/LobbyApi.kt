package com.example.toaudio.data.remote.lobby

import kotlinx.coroutines.Deferred
import retrofit2.http.Headers
import retrofit2.http.POST

interface LobbyApi {

    @POST("/api/Room")
    @Headers("Content-Type: application/json")
    fun getRoom(): Deferred<LobbyResponse>

}