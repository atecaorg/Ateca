package com.ateca.ui.screens.settings.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
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
import com.ateca.ui.components.topappbar.NoteAppTopBar
import com.ateca.ui.screens.settings.view.components.SettingsCard
import com.ateca.ui.screens.settings.view.components.SoftwareInfoDialog
import com.ateca.ui.screens.settings.view.components.ThemePicker
import com.ateca.ui.screens.settings.view.components.ThemeSettingBlock
import com.ateca.ui.screens.settings.viewmodel.SettingsViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingsScreen(
    navController: NavHostController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
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
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues = paddingValues)
            ) {
                ThemePicker(viewModel = viewModel)

                Text(
                    text = stringResource(R.string.theme),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                ThemeSettingBlock(
                    selectedTheme = viewModel.settings.collectAsState().value.theme,
                    onThemeClicked = { theme ->
                        viewModel.setThemeSetting(theme = theme)
                    }
                )

                Divider(modifier = Modifier.padding(5.dp))

                SettingsCard(
                    cardTitleText = stringResource(R.string.About),
                    cardText = stringResource(R.string.software_license),
                    cardTextColor = MaterialTheme.colors.error
                ) {
                    showLicenseDialog = true
                }
            }

        }
    )
}

@Preview(backgroundColor = 0xFF64dd17, showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}