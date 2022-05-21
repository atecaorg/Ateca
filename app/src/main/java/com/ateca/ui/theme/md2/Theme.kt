package com.ateca.ui.theme.md2

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.ateca.ui.theme.LocalSpacing
import com.ateca.ui.theme.Spacing

private val LightColorPalette = lightColors(
    background = White,
    primary = Brown14,
    primaryVariant = BlackOlive,
    secondary = White,
    secondaryVariant = OliveGrey,
    onPrimary = BlackOlive
)

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    background = TransportBlack,
    primary = RedOrange14,
    primaryVariant = PearlWhite,
    secondary = BlackOliveDark,
    secondaryVariant = GreySilk,
    onPrimary = PearlWhite
)

@Composable
fun AtecaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}