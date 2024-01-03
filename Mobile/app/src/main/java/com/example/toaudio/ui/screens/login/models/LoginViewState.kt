package com.example.toaudio.ui.screens.login.models


enum class LoginSubState{
    SignIn, SignUp, Forgot
}
data class LoginViewState (
    val loginSubState: LoginSubState = LoginSubState.SignIn,
    val usernameValue: String = "",
    val passwordValue: String = "",
    val fullNameValue: String = "",
    val isProgress: Boolean = false,
)