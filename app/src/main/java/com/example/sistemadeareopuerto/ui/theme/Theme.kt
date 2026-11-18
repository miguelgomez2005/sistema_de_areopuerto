package com.example.sistemadeareopuerto.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = DeepBlue,
    secondary = GoldAccent,
    background = android.graphics.Color.parseColor("#F5F6F8").let { androidx.compose.ui.graphics.Color(it) }
)

@Composable
fun SistemadeareopuertoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}