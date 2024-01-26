package com.example.toaudio.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.toaudio.player.service.ToAudioService
import com.example.toaudio.ui.screens.ApplicationScreen
import com.example.toaudio.ui.theme.ToAudioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isServiceRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService()
        setContent {
            ToAudioTheme {
                ApplicationScreen()
            }
        }
    }

    private fun startService() {
        if (!isServiceRunning) {
            val intent = Intent(this, ToAudioService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }
            isServiceRunning = true
        }
    }
}

