package com.ateca.domain.core

/**
 * Created by dronpascal on 16.05.2022.
 */
sealed class UIComponent {

    data class Dialog(
        val title: String,
        val description: String,
    ) : UIComponent()

    data class None(
        val message: String,
    ) : UIComponent()
}