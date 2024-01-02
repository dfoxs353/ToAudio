package com.example.toaudio.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toaudio.common.EventHandler
import com.example.toaudio.data.repository.AuthRepository
import com.example.toaudio.ui.screens.login.models.LoginEvent
import com.example.toaudio.ui.screens.login.models.LoginSubState
import com.example.toaudio.ui.screens.login.models.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel(), EventHandler<LoginEvent> {

    private val _viewState = MutableLiveData(LoginViewState())
    val viewState: LiveData<LoginViewState>  = _viewState


    override fun obtainEvent(event: LoginEvent) {
        when(event){
            LoginEvent.SignInClicked -> logIn()
            is LoginEvent.SignUpClicked -> signUp()
            is LoginEvent.EmailChanged -> emailChanged(event.value)
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


    private fun logIn() {
       viewModelScope.launch(Dispatchers.IO) {
           _viewState.postValue(
               _viewState.value?.copy(isProgress = true)
           )

           val result = authRepository.login(
               username = _viewState.value!!.emailValue,
               password = _viewState.value!!.passwordValue
           )

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
            delay(3000L)
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

    private fun emailChanged(value: String) {
        _viewState.postValue(
            _viewState.value?.copy(emailValue = value)
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