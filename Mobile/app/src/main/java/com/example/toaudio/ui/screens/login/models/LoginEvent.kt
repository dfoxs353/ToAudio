package com.example.toaudio.ui.screens.login.models

sealed class LoginEvent {
    object ActionSwitch: LoginEvent()
    object SignInClicked : LoginEvent()
    object SignUpClicked : LoginEvent()
    data class UserNameChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String): LoginEvent()
}