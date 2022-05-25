package com.ateca.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ateca.UserSettings
import com.ateca.domain.datasource.ISettingsDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(protoDataSource: ISettingsDataSource) : ViewModel() {

    private val _settings: MutableStateFlow<UserSettings> =
        MutableStateFlow(UserSettings.getDefaultInstance())
    val settings: StateFlow<UserSettings> = _settings

    init {
        viewModelScope.launch(Dispatchers.IO) {
            protoDataSource.getSetting().collect {
                _settings.value = it
            }
        }
    }
}