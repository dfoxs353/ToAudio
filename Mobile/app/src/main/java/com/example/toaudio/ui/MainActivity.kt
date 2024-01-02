package com.example.toaudio.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.toaudio.ui.screens.ApplicationScreen
import com.example.toaudio.ui.theme.ToAudioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToAudioTheme {
                ApplicationScreen()
            }
        }
    }
}

