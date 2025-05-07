package com.ordresot.diabetessupporter.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.lightColors
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = DarkGreen,
    onPrimary = White,

    secondary = Green,
    secondaryVariant = DarkGreen,
    onSecondary = White,

    background = White,
    surface = WhisperGray,
    onBackground = Black,
    onSurface = Black,
)

@Composable
fun DiabetesAppTheme(
    darkTheme: Boolean = false, // можно переключать, если нужно
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}
