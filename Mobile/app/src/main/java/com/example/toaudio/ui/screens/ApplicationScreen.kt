package com.example.toaudio.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toaudio.ui.navigation.NavigationTree
import com.example.toaudio.ui.screens.login.LoginScreen
import com.example.toaudio.ui.screens.login.LoginViewModel
import com.example.toaudio.ui.screens.rooms.RoomsScreen
import com.example.toaudio.ui.screens.rooms.RoomsViewModel
import com.example.toaudio.ui.screens.splash.SplashScreen

@Composable
fun ApplicationScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Splash.name){

        composable(NavigationTree.Splash.name){ SplashScreen(navController) }
        composable(NavigationTree.Login.name) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                modifier = Modifier
                    .padding(horizontal = 44.dp)
                    .fillMaxSize(),
                loginViewModel = loginViewModel,
                navController = navController,
            )
        }
        composable(NavigationTree.Rooms.name){
            val roomsViewModel = hiltViewModel<RoomsViewModel>()
            RoomsScreen(
                modifier = Modifier
                    .padding(horizontal = 44.dp)
                    .fillMaxSize(),
                loginViewModel = loginViewModel,
                navController = navController,
            )
        }

    }
}