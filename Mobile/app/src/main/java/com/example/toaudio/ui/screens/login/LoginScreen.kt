package com.example.toaudio.ui.screens.login

import android.widget.Toast
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.toaudio.R
import com.example.toaudio.data.models.Result
import com.example.toaudio.ui.navigation.NavigationTree
import com.example.toaudio.ui.screens.login.models.LoginEvent
import com.example.toaudio.ui.screens.login.models.LoginSubState
import com.example.toaudio.ui.screens.login.views.ForgotView
import com.example.toaudio.ui.screens.login.views.SignInView
import com.example.toaudio.ui.screens.login.views.SignUpView
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
    navController: NavController,
){
    val viewState = loginViewModel.viewState.observeAsState()

    val context = LocalContext.current
    val welcomeText = stringResource(id = R.string.welcome)
    val errorText = stringResource(id = R.string.error_auth_string)

    LaunchedEffect(loginViewModel, context) {
        loginViewModel.authResults.collect{result ->
            when(result){
                is Result.Error -> {
                    Toast.makeText(
                        context,
                        "$errorText",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Result.Success -> {

                    Toast.makeText(
                        context,
                        "$welcomeText ${result.data.user.username}",
                        Toast.LENGTH_LONG
                    ).show()
                    delay(3000L)
                    navController.navigate(NavigationTree.Rooms.name)
                }
            }
        }
    }

    Surface(
        modifier = modifier,
    ) {
        with(viewState.value!!){
            when(loginSubState){
                LoginSubState.SignIn -> {
                    SignInView(
                        emailValue = usernameValue,
                        passwordValue = passwordValue,
                        onUserNameFieldChange = { loginViewModel.obtainEvent(LoginEvent.UserNameChanged(it)) },
                        onPasswordFieldChange = {  loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it)) },
                        signInClick = {loginViewModel.obtainEvent(LoginEvent.SignInClicked)},
                        signUpClick = {loginViewModel.obtainEvent(LoginEvent.ActionSwitch)},
                        isProgress = isProgress,
                    )
                }
                LoginSubState.SignUp -> {
                    SignUpView(
                        emailValue = usernameValue,
                        passwordValue = passwordValue,
                        onUserNameFieldChange = { loginViewModel.obtainEvent(LoginEvent.UserNameChanged(it)) },
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