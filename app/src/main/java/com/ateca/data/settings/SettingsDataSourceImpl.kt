package com.ateca.data.settings

import androidx.datastore.core.DataStore
import com.ateca.domain.datasource.ISettingsDataSource
import com.ateca.domain.models.ApplicationSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<ApplicationSettings>
) : ISettingsDataSource {
    private val _applicationSettings = MutableStateFlow(ApplicationSettings())
    val applicationSettings: StateFlow<ApplicationSettings> = _applicationSettings

    override suspend fun getSetting(): ApplicationSettings {
        dataStore.data.collect { settings ->
            _applicationSettings.value = settings
        }
        return applicationSettings.value
    }

    override suspend fun setSetting(settings: ApplicationSettings) {
        dataStore.updateData {
            settings
        }
        _applicationSettings.value = settings
    }
}