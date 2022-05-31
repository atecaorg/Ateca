package com.ateca.domain.interactors.util

import com.ateca.BuildConfig

/**
 * Created by dronpascal on 24.05.2022.
 */
internal fun debugBehavior(isThrowingError: Boolean = false) {
    if (BuildConfig.DEBUG) {
        if (isThrowingError) throw Exception("Debug")
    }
}