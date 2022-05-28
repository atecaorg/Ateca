package com.ateca.ui.screens.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ateca.domain.datasource.ISettingsDataSource
import com.ateca.domain.entity.Theme
import com.ateca.domain.models.ApplicationSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsDataSource: ISettingsDataSource
) : ViewModel() {
    private val ioDispatcher = Dispatchers.IO

    private val _settings: MutableStateFlow<ApplicationSettings> =
        MutableStateFlow(ApplicationSettings())

    val settings: StateFlow<ApplicationSettings> = _settings

    init {
        viewModelScope.launch(ioDispatcher) {
            settingsDataSource.getSetting().collect {
                _settings.value = it
            }
        }
    }

    fun setThemeSetting(theme: Theme) {
        setSettings(
            applicationSettings = ApplicationSettings(theme = theme)
        )
    }

    private fun setSettings(applicationSettings: ApplicationSettings) {
        viewModelScope.launch(ioDispatcher) {
            settingsDataSource.setSetting(applicationSettings)
        }
    }
}