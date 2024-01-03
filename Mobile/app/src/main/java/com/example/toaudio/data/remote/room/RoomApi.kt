package com.example.toaudio.data.remote.room

import kotlinx.coroutines.Deferred
import retrofit2.http.Headers
import retrofit2.http.POST

interface RoomApi {

    @POST("/api/Room")
    @Headers("Content-Type: application/json")
    fun getRoom(): Deferred<RoomResponse>

}