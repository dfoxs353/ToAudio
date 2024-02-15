package com.example.toaudio.data.repository

import android.content.SharedPreferences
import com.example.toaudio.domain.models.User
import com.example.toaudio.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalUserRepository {

    private val KEY_ACCESS_TOKEN = "access_token"
    private val KEY_USER_ID = "user_id"
    private val KEY_USER_NAME = "user_name"

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()



    override suspend fun saveUser(user: User) {
        with(editor) {
            putString(KEY_USER_ID, user.userid)
            putString(KEY_ACCESS_TOKEN, user.accessToken)
            putString(KEY_USER_NAME, user.userName)
            apply()
        }

    }


    override suspend fun getUser(): User? {
        val userId = getUserId()
        val accessToken = getAccessToken()
        val userName = getUserName()


        return if (userId.isNullOrEmpty() || accessToken.isNullOrEmpty() || userName.isNullOrEmpty()) {
            null
        } else User(
            userid = userId,
            accessToken = accessToken,
            userName = userName,
        )
    }

    override suspend fun saveJWToken(accessToken: String) {
        with(editor) {
            putString(KEY_ACCESS_TOKEN, accessToken)
            apply()
        }

    }

    override fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    override fun getUserName(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, null)
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun clearUserData() {
        with(editor) {
            remove(KEY_ACCESS_TOKEN)
            remove(KEY_USER_ID)
            remove(KEY_USER_NAME)
            apply()
        }
    }
}