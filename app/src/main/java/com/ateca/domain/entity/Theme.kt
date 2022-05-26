package com.ateca.domain.entity

/**
 * Created by dronpascal on 24.05.2022.
 */
enum class Theme(val code: Int) {
    SYSTEM(0), DARK(1), LIGHT(2)
}

fun Int?.toTheme(): Theme =
    when (this) {
        Theme.SYSTEM.code -> Theme.SYSTEM
        Theme.DARK.code -> Theme.DARK
        Theme.LIGHT.code -> Theme.LIGHT
        else -> Theme.SYSTEM
    }

fun Theme.toInt(): Int =
    when (this) {
        Theme.SYSTEM -> Theme.SYSTEM.code
        Theme.DARK -> Theme.DARK.code
        Theme.LIGHT -> Theme.LIGHT.code
    }