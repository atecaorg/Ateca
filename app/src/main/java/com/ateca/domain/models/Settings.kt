package com.ateca.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val theme: Theme = Theme.SYSTEM
)

enum class Theme {
    LIGHT,
    DARK,
    SYSTEM
}
