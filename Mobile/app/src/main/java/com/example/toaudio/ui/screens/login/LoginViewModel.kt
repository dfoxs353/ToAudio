package com.example.toaudio.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toaudio.common.EventHandler
import com.example.toaudio.data.models.User
import com.example.toaudio.data.models.Result
import com.example.toaudio.data.remote.auth.AuthResponse
import com.example.toaudio.data.repository.AuthRepository
import com.example.toaudio.data.repository.LocalUserRepository
import com.example.toaudio.ui.screens.login.models.LoginEvent
import com.example.toaudio.ui.screens.login.models.LoginSubState
import com.example.toaudio.ui.screens.login.models.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: LocalUserRepository,
) : ViewModel(), EventHandler<LoginEvent> {

    private val _viewState = MutableLiveData(LoginViewState())
    val viewState: LiveData<LoginViewState>  = _viewState

    private val resultChannel = Channel<Result<AuthResponse>>()
    val authResults = resultChannel.receiveAsFlow()


    override fun obtainEvent(event: LoginEvent) {
        when(event){
            LoginEvent.SignInClicked -> signIn()
            is LoginEvent.SignUpClicked -> signUp()
            is LoginEvent.UserNameChanged -> usernameChanged(event.value)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value)
            is LoginEvent.ActionSwitch -> actionSwitch()
        }
    }

    private fun actionSwitch() {
        when(_viewState.value!!.loginSubState){
            LoginSubState.SignUp -> fetchSignIn()
            LoginSubState.SignIn -> fetchSignUp()
            LoginSubState.Forgot -> fetchForgot()
        }
    }


    private fun signIn() {
       viewModelScope.launch(Dispatchers.IO) {
           _viewState.postValue(
               _viewState.value?.copy(isProgress = true)
           )

           val result = authRepository.signin(
               username = _viewState.value!!.usernameValue,
               password = _viewState.value!!.passwordValue
           )

           when(result){
               is Result.Error -> userRepository.clearUserData()
               is Result.Success -> with(result.data){
                   userRepository.saveUser(User(user.id,user.username,access_token))
               }
           }

           resultChannel.send(result)

           _viewState.postValue(
               _viewState.value?.copy(isProgress = false)
           )
       }
    }

    private fun signUp() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(
                _viewState.value?.copy(isProgress = true)
            )

            val result = authRepository.signup(
                username = _viewState.value!!.usernameValue,
                password = _viewState.value!!.passwordValue
            )


            when(result){
                is Result.Error -> userRepository.clearUserData()
                is Result.Success -> with(result.data){
                    userRepository.saveUser(User(user.id,user.username,access_token))
                }
            }

            resultChannel.send(result)

            _viewState.postValue(
                _viewState.value?.copy(isProgress = false)
            )
        }
    }
    private fun passwordChanged(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(passwordValue = value)
        )
    }

    private fun usernameChanged(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(usernameValue = value)
        )
    }

    private fun fetchForgot(){
        _viewState.postValue(
            _viewState.value?.copy(loginSubState = LoginSubState.Forgot)
        )
    }

    private fun fetchSignIn(){
        _viewState.postValue(
            _viewState.value?.copy(loginSubState = LoginSubState.SignIn)
        )
    }

    private fun fetchSignUp(){
        _viewState.postValue(
            _viewState.value?.copy(loginSubState = LoginSubState.SignUp)
        )
    }
}