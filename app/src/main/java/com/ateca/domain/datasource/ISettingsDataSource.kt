package com.ateca.domain.datasource

import com.ateca.UserSettings
import kotlinx.coroutines.flow.Flow


interface ISettingsDataSource {
    suspend fun getSetting(): Flow<UserSettings>
    suspend fun setSetting(settings: UserSettings)
}