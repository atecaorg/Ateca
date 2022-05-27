package com.ateca.ui.screens.settings.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ateca.R
import com.ateca.domain.entity.Theme
import com.ateca.ui.components.topappbar.NoteAppTopBar
import com.ateca.ui.screens.settings.view.components.*
import com.ateca.ui.screens.settings.viewmodel.SettingsViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val currentTheme = viewModel.settings.collectAsState().value.theme
    val showThemeDialog = remember { mutableStateOf(false) }
    val themeName = remember { mutableStateOf(" ") }

    themeName.value = when (currentTheme) {
        Theme.DARK -> stringResource(R.string.dark)
        Theme.LIGHT -> stringResource(R.string.light)
        else -> stringResource(R.string.system)
    }

    if (showThemeDialog.value) {
        ThemeSettingsDialog(
            currentTheme = currentTheme,
            onThemeChoose = { theme ->
                viewModel.setThemeSetting(theme = theme)
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
                onBackPressed = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            SettingsTitleText(
                text = stringResource(R.string.settings)
            )
            SettingsRow(
                settingsTitle = stringResource(R.string.theme),
                settingsValue = themeName.value
            ) {
                showThemeDialog.value = true
            }

            Divider(modifier = Modifier.padding(5.dp))

            SettingsTitleText(
                text = stringResource(R.string.About)
            )
            SettingsRow(
                settingsTitle = stringResource(R.string.software_license),
                onClick = { showLicenseDialog = true }
            )
        }

    }
}

@Preview(backgroundColor = 0xFF64dd17, showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}