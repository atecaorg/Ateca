package com.ateca.domain.core

/**
 * Created by dronpascal on 16.05.2022.
 */
sealed class UIComponent {

    data class Dialog(
        val title: UIText,
        val description: UIText,
    ) : UIComponent()

    data class None(
        val message: UIText,
    ) : UIComponent()
}