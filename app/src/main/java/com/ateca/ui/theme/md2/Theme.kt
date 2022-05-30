package com.ateca.ui.theme.md2

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalWindowInfo
import com.ateca.ui.theme.LocalSpacing
import com.ateca.ui.theme.Spacing
import com.ateca.ui.theme.mappers.toMD2
import com.ateca.ui.theme.md3.AtecaDarkColorScheme
import com.ateca.ui.theme.md3.AtecaLightColorScheme
import com.ateca.ui.theme.md3.AtecaShapes
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightGrayishTheme = AtecaLightColorScheme.toMD2()

private val DarkGrayishTheme = AtecaDarkColorScheme.toMD2()

@Composable
fun AtecaTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) {
        DarkGrayishTheme
    } else {
        LightGrayishTheme
    }

    val systemUiController = rememberSystemUiController()
    val useDarkIcons: Boolean = !isDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.surface,
            darkIcons = useDarkIcons
        )
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = AtecaShapes.toMD2(),
            content = content
        )
    }
}