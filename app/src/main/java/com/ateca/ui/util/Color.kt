package com.ateca.ui.util

import androidx.compose.ui.graphics.Color

/**
 * Created by dronpascal on 24.05.2022.
 */
internal fun Color.darker(factor: Float): Color {
    return Color(
        alpha = this.alpha,
        red = this.red * factor,
        green = this.green * factor,
        blue = this.blue * factor
    )
}