package com.ateca.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.ateca.UserSettings
import com.ateca.ui.components.app.AtecaApp
import com.ateca.ui.theme.md2.AtecaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val composeTheme = viewModel.settings.collectAsState().value.theme
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