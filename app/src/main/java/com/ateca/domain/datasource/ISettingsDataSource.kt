package com.ateca.domain.datasource

import com.ateca.domain.core.Theme
import com.ateca.domain.models.ApplicationSettings


interface ISettingsDataSource {
    suspend fun getSetting(): ApplicationSettings
    suspend fun setSetting(settings: ApplicationSettings)
}