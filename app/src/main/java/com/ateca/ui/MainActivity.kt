package com.ateca.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.ateca.UserSettings
import com.ateca.domain.datasource.ISettingsDataSource
import com.ateca.ui.components.app.AtecaApp
import com.ateca.ui.theme.md2.AtecaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: ISettingsDataSource
    private val settings = MutableStateFlow<UserSettings>(UserSettings.getDefaultInstance())

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            dataStore.getSetting().collect {
                settings.value = it
            }
        }

        setContent {
            val composeTheme = settings.collectAsState().value.theme
            val isDark = mutableStateOf(
                when (composeTheme) {
                    UserSettings.Theme.DARK -> true
                    UserSettings.Theme.LIGHT -> false
                    else -> isSystemInDarkTheme()
                }
            )
            AtecaTheme(darkTheme = isDark.value) {
                Box {
                    AtecaApp()
                }
            }
        }
    }
}