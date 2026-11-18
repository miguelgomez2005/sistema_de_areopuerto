package com.example.sistemadeareopuerto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sistemadeareopuerto.ui.AirportApp
import com.example.sistemadeareopuerto.ui.theme.DeepBlue
import com.example.sistemadeareopuerto.ui.theme.GoldAccent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = DeepBlue,
                    secondary = GoldAccent,
                    background = Color(0xFFF5F6F8)
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AirportApp()
                }
            }
        }
    }
}