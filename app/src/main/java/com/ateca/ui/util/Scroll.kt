package com.ateca.ui.util

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState

/**
 * Created by dronpascal on 21.05.2022.
 */
fun ScrollState.isScrollInInitialState(): Boolean = value == 0

fun LazyListState.isScrollInInitialState(): Boolean =
    firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset == 0