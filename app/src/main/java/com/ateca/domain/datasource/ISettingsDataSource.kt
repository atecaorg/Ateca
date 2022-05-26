package com.ateca.domain.datasource

import com.ateca.domain.models.ApplicationSettings
import kotlinx.coroutines.flow.Flow


interface ISettingsDataSource {
    suspend fun getSetting(): Flow<ApplicationSettings>
    suspend fun setSetting(settings: ApplicationSettings)
}