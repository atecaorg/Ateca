package com.ateca.ui.theme.mappers

import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.luminance

fun ColorScheme.toMD2() = Colors(
    primary = this.primary,
    primaryVariant = this.primaryContainer,
    secondary = this.secondary,
    secondaryVariant = this.secondaryContainer,
    background = this.background,
    surface = this.surface,
    error = this.error,
    onPrimary = this.onPrimary,
    onSecondary = this.onSecondary,
    onBackground = this.onBackground,
    onSurface = this.onSurface,
    onError = this.onError,
    isLight = this.background.luminance() > 0.5
)