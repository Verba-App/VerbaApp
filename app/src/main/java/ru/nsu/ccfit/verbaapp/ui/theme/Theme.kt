package ru.nsu.ccfit.verbaapp.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



object VerbaTheme{
     val DarkColorPalette = darkColors(
        primary = Purple200,
        primaryVariant = Purple700,
        secondary = Teal200
    )

     val LightColorPalette = lightColors(
        primary = Color(3, 147, 235, 255),
        surface = Color.White,
        background = Color(3, 147, 235, 255),
        primaryVariant = Purple700,
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
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}