package dev.kiarielinus.wordleclone.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val WordleColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = FillBlack,
    surface = FillBlack
)

@Composable
fun WordleCloneTheme(content: @Composable () -> Unit) {
    val colors = WordleColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}