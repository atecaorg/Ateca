package com.ateca.data.local.prefs

import androidx.datastore.core.DataStore
import com.ateca.domain.datasource.ISettingsDataSource
import com.ateca.domain.models.ApplicationSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<ApplicationSettings>
) : ISettingsDataSource {

    override suspend fun getSetting(): Flow<ApplicationSettings> = dataStore.data

    override suspend fun setSetting(settings: ApplicationSettings) {
        dataStore.updateData {
            settings
        }
    }
}