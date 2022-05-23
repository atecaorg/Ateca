package com.ateca.ui.theme.md2

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.ateca.ui.theme.LocalSpacing
import com.ateca.ui.theme.Spacing
import com.ateca.ui.theme.mappers.toMD2
import com.ateca.ui.theme.md3.AtecaDarkColorScheme
import com.ateca.ui.theme.md3.AtecaLightColorScheme

private val LightGrayishTheme = AtecaLightColorScheme.toMD2()

private val DarkGrayishTheme = AtecaDarkColorScheme.toMD2()

@Composable
fun AtecaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkGrayishTheme
    } else {
        LightGrayishTheme
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