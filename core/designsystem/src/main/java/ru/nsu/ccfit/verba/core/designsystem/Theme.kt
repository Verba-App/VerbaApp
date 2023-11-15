package ru.nsu.ccfit.verba.core.designsystem

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



object VerbaAppTheme{
    val DarkColorPalette = darkColors(
        primary = Purple200,
        primaryVariant = Purple700,
        secondary = Teal200
    )

    val LightColorPalette = lightColors(
        primary = Color(3, 148, 235, 255),
        surface = Color.White,
        background = Color(3, 148, 235, 255),
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
        VerbaAppTheme.DarkColorPalette
    } else {
        VerbaAppTheme.LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
