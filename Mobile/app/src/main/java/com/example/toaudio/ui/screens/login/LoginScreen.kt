package com.example.toaudio.ui.screens.login

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.toaudio.ui.screens.login.models.LoginEvent
import com.example.toaudio.ui.screens.login.models.LoginSubState
import com.example.toaudio.ui.screens.login.views.ForgotView
import com.example.toaudio.ui.screens.login.views.SignInView
import com.example.toaudio.ui.screens.login.views.SignUpView

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
    navController: NavController,
){
    val viewState = loginViewModel.viewState.observeAsState()

    Surface(
        modifier = modifier,
    ) {
        with(viewState.value!!){
            when(loginSubState){
                LoginSubState.SignIn -> {
                    SignInView(
                        emailValue = emailValue,
                        passwordValue = passwordValue,
                        onUserNameFieldChange = { loginViewModel.obtainEvent(LoginEvent.EmailChanged(it)) },
                        onPasswordFieldChange = {  loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it)) },
                        signInClick = {loginViewModel.obtainEvent(LoginEvent.SignInClicked)},
                        signUpClick = {loginViewModel.obtainEvent(LoginEvent.ActionSwitch)},
                        isProgress = isProgress,
                    )
                }
                LoginSubState.SignUp -> {
                    SignUpView(
                        emailValue = emailValue,
                        passwordValue = passwordValue,
                        onUserNameFieldChange = { loginViewModel.obtainEvent(LoginEvent.EmailChanged(it)) },
                        onPasswordFieldChange = {  loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it)) },
                        signUpClick = {loginViewModel.obtainEvent(LoginEvent.SignUpClicked)},
                        signInClick = {loginViewModel.obtainEvent(LoginEvent.ActionSwitch)},
                        isProgress = isProgress,
                    )
                }
                LoginSubState.Forgot -> {
                    ForgotView()
                }
            }
        }
    }
}