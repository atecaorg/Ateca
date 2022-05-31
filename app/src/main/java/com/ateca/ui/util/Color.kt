package com.ateca.ui.util

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color

/**
 * Created by dronpascal on 24.05.2022.
 */
internal fun Color.darker(
    @FloatRange(from = 0.0, to = 1.0)
    factor: Float
): Color {
    return Color(
        alpha = this.alpha,
        red = this.red * factor,
        green = this.green * factor,
        blue = this.blue * factor
    )
}