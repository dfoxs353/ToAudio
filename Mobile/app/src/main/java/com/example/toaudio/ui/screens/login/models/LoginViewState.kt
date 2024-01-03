package com.example.toaudio.ui.screens.login.models


enum class LoginSubState{
    SignIn, SignUp, Forgot
}
data class LoginViewState (
    var loginSubState: LoginSubState = LoginSubState.SignIn,
    var usernameValue: String = "",
    var passwordValue: String = "",
    var fullNameValue: String = "",
    var isProgress: Boolean = false,
)