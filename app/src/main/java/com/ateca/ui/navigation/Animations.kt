package com.ateca.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

/**
 * Created by dronpascal on 21.05.2022.
 */
object Animations {

    private const val duration = 700
    private const val delay = 400

    val enterTransition = fadeIn(
        animationSpec = tween(durationMillis = duration, delayMillis = delay)
    )
    val exitTransition = fadeOut(
        animationSpec = tween(durationMillis = duration, delayMillis = delay)
    )
}