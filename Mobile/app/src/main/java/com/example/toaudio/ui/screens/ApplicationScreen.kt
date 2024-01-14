package com.example.toaudio.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.toaudio.ui.navigation.NavigationTree
import com.example.toaudio.ui.screens.login.LoginScreen
import com.example.toaudio.ui.screens.login.LoginViewModel
import com.example.toaudio.ui.screens.room.RoomScreen
import com.example.toaudio.ui.screens.room.RoomViewModel
import com.example.toaudio.ui.screens.rooms.RoomsScreen
import com.example.toaudio.ui.screens.rooms.RoomsViewModel
import com.example.toaudio.ui.screens.splash.SplashScreen

@Composable
fun ApplicationScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Splash.route){

        composable(NavigationTree.Splash.route){ SplashScreen(navController) }
        composable(NavigationTree.Login.route) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                modifier = Modifier
                    .padding(horizontal = 44.dp)
                    .fillMaxSize(),
                loginViewModel = loginViewModel,
                navController = navController,
            )
        }
        composable(NavigationTree.Rooms.route){
            val roomsViewModel = hiltViewModel<RoomsViewModel>()
            RoomsScreen(
                modifier = Modifier
                    .padding(horizontal = 44.dp)
                    .fillMaxSize(),
                roomsViewModel = roomsViewModel,
                navController = navController,
            )
        }
        composable(
            "${NavigationTree.Room.route}/{roomId}",
            arguments = listOf(navArgument("roomId"){type = NavType.StringType})
        ){
            RoomScreen(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxSize(),
                navController = navController,
            )
        }

    }
}