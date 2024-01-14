package com.example.toaudio.ui.screens.splash

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.toaudio.ui.navigation.NavigationTree
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
){
    LaunchedEffect(key1 = Unit, block = {
        delay(1000L)
        navController.navigate(NavigationTree.Login.route)
    })
}