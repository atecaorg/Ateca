package com.ateca.domain.models

import com.ateca.domain.entity.Theme
import kotlinx.serialization.Serializable

@Serializable
data class ApplicationSettings(
    val theme: Theme = Theme.SYSTEM
)
