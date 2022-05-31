package com.ateca.domain.core

/**
 * Created by dronpascal on 18.05.2022.
 */
sealed class UIText {

    data class DynamicString(val value: String) : UIText()

    class StringResource(
        val resId: Int,
        vararg val args: Any
    ) : UIText()
}