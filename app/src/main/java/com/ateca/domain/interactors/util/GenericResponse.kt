package com.ateca.domain.interactors.util

import androidx.annotation.StringRes
import com.ateca.domain.core.DataState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.core.UIText

/**
 * Created by dronpascal on 31.05.2022.
 */
internal fun <T> genericDialogResponse(
    e: Exception,
    @StringRes title: Int,
    @StringRes description: Int
): DataState.Response<T> =
    DataState.Response(
        uiComponent = UIComponent.Dialog(
            title = UIText.StringResource(title),
            description = e.message
                ?.let { UIText.DynamicString(it) }
                ?: UIText.StringResource(description)
        )
    )