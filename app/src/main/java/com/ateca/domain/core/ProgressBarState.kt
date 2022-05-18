package com.ateca.domain.core

/**
 * Created by dronpascal on 16.05.2022.
 */
sealed class ProgressBarState {

    object Loading : ProgressBarState()

    object Idle : ProgressBarState()
}