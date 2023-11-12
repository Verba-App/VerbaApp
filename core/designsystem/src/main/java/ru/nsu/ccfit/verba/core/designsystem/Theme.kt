package ru.nsu.ccfit.verba.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


object VerbaTheme {
    val DarkColorPalette = darkColorScheme(
        primary = Purple200,
        secondary = Teal200
    )

    val LightColorPalette = lightColorScheme(
        primary = Color(3, 147, 235, 255),
        surface = Color.White,
        background = Color(3, 147, 235, 255),
        secondary = Teal200,
        onSecondary = Color.Black,
        onSurface = Color.Black,
    )
}

@Composable
fun VerbaAppAndroidTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        VerbaTheme.DarkColorPalette
    } else {
        VerbaTheme.LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}