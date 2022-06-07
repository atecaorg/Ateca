package com.ateca.ui.screens.settings.view

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.ateca.BuildConfig
import com.ateca.R
import com.ateca.domain.entity.Theme
import com.ateca.domain.models.ApplicationSettings
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.components.topappbar.NoteAppTopBar
import com.ateca.ui.screens.settings.view.components.SettingsGroup
import com.ateca.ui.screens.settings.view.components.SettingsTile
import com.ateca.ui.screens.settings.view.components.SoftwareInfoDialog
import com.ateca.ui.screens.settings.view.components.ThemeSettingsDialog
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingsScreen(
    navController: NavHostController? = null,
    getSettingsFlow: () -> StateFlow<ApplicationSettings>? = { null },
    setThemeSetting: (Theme) -> Unit = {}
) {
    val currentTheme = getSettingsFlow()?.run { collectAsState().value.theme }
    val showThemeDialog = remember { mutableStateOf(false) }
    val themeName = remember { mutableStateOf(" ") }

    themeName.value = when (currentTheme) {
        Theme.DARK -> stringResource(R.string.dark)
        Theme.LIGHT -> stringResource(R.string.light)
        else -> stringResource(R.string.system)
    }

    if (showThemeDialog.value) {
        ThemeSettingsDialog(
            currentTheme = currentTheme!!,
            onThemeChoose = { theme ->
                setThemeSetting(theme)
                showThemeDialog.value = false
            }
        ) {
            showThemeDialog.value = false
        }
    }

    var showLicenseDialog: Boolean by rememberSaveable { mutableStateOf(false) }

    if (showLicenseDialog) {
        SoftwareInfoDialog { showLicenseDialog = false }
    }

    Scaffold(
        topBar = {
            NoteAppTopBar(
                withBackIcon = true,
                onBackPressed = { navController?.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            SettingsGroup(
                text = stringResource(R.string.settings)
            )
            SettingsTile(
                settingsTitle = stringResource(R.string.theme),
                settingsValue = themeName.value
            ) {
                showThemeDialog.value = true
            }

            Divider(
                modifier = Modifier
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                        horizontal = MaterialTheme.spacing.medium
                    )
            )

            SettingsGroup(
                text = stringResource(R.string.About)
            )
            SettingsTile(
                settingsTitle = stringResource(R.string.software_license),
                onClick = { showLicenseDialog = true }
            )
            if (BuildConfig.DEBUG) {
                SettingsGroup(
                    text = "Debug"
                )
                SettingsTile(
                    settingsTitle = "Crash the app",
                    onClick = { throw RuntimeException("Test Crash") }
                )
            }
        }

    }
}

@Preview(
    name = "SelectedNoteListScreenLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Preview(
    name = "SelectedNoteListScreenDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Preview(
    name = "SelectedNoteListScreenLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Composable
private fun SettingsScreenPreview() {
    AtecaTheme {
        SettingsScreen()
    }
}