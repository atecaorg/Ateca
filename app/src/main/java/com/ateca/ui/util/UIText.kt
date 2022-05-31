package com.ateca.ui.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ateca.domain.core.UIText

/**
 * Created by dronpascal on 31.05.2022.
 */
@Composable
fun UIText.asString(): String {
    return when (this) {
        is UIText.DynamicString -> value
        is UIText.StringResource -> {
            @StringRes val resId = this.resId
            stringResource(resId, *args)
        }
    }
}

fun UIText.asString(context: Context): String {
    return when (this) {
        is UIText.DynamicString -> value
        is UIText.StringResource -> {
            @StringRes val resId = this.resId
            context.getString(resId, *args)
        }
    }
}