package com.ateca.ui.components

/**
 * Created by dronpascal on 20.05.2022.
 */
object AppPreviewConstants {

    const val PREVIEW_LIGHT_THEME_BACKGROUND_COLOR = 0xFFfffcf3
    const val PREVIEW_DARK_THEME_BACKGROUND_COLOR = 0xFF1d1c16

    const val PREVIEW_FONT_SCALE = 2f

    const val title = "Lorem ipsum"

    val text by lazy {
        "Lorem ipsum " + "lorem ipsum ".repeat(10)
    }
}