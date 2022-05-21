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


@SuppressLint("ConflictingOnColor")
private val LightGrayishTheme = lightColors(
    primary = LightGrayishOrange,
    primaryVariant = White,
    secondary = VeryDarkGrayishOrange,
    secondaryVariant = DarkGrayishOrange,
    onPrimary = VeryDarkGrayishOrangeText,
    onSecondary = LightGrayishOrange100,
    background = White
)

@SuppressLint("ConflictingOnColor")
private val DarkGrayishTheme = darkColors(
    primary = GrayishOrange,
    primaryVariant = White,
    secondary = MostlyBlackOrange,
    secondaryVariant = DarkGrayishOrange,
    onPrimary = VeryDarkGrayishOrangeText,
    onSecondary = LightGrayishOrange100,
    background = White
)

@SuppressLint("ConflictingOnColor")
private val LightMostlyBlackTheme = darkColors(
    primary = VeryMostlyBlack,
    primaryVariant = VeryDarkGray,
    secondary = VeryDarkGrayishOrange,
    secondaryVariant = DarkGrayishOrange,
    onPrimary = GrayishOrange100,
    onSecondary = LightGrayishOrange100,
    background = VeryDarkGray
)

@SuppressLint("ConflictingOnColor")
private val DarkMostlyBlackTheme = darkColors(
    primary = Black,
    primaryVariant = VeryDarkGray,
    secondary = MostlyBlackOrange,
    secondaryVariant = DarkGrayishOrange,
    onPrimary = GrayishOrange100,
    onSecondary = LightGrayishOrange100,
    background = VeryDarkGray
)

@SuppressLint("ConflictingOnColor")
private val LightGrayishBlueTheme = darkColors(
    primary = LightGrayishBlue,
    primaryVariant = White,
    secondary = LightGrayishBlue100,
    secondaryVariant = White,
    onPrimary = GrayishOrange100,
    onSecondary = LightGrayishOrange100,
    background = VeryDarkGray
)

@SuppressLint("ConflictingOnColor")
private val DarkGrayishBlueTheme = darkColors(
    primary = GrayishBlue,
    primaryVariant = White,
    secondary = DarkGrayishCyan,
    secondaryVariant = White,
    onPrimary = DarkGrayishBlue,
    onSecondary = VeryDarkGrayishBlue,
    background = White
)

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