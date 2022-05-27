package com.ateca.ui.screens.settings.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ateca.R
import com.ateca.domain.entity.Theme
import com.ateca.ui.screens.settings.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemePicker(
    viewModel: SettingsViewModel
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
        ThemeChooseDialog(
            currentTheme = currentTheme,
            onThemeChoose = { theme ->
                viewModel.setThemeSetting(theme = theme)
                showThemeDialog.value = false
            }
        ) {
            showThemeDialog.value = false
        }
    }

    Text(
        text = stringResource(R.string.settings),
        color = MaterialTheme.colors.error,
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp),
        style = MaterialTheme.typography.body1
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp),
        border = BorderStroke(0.dp, Color.Transparent),
        onClick = { showThemeDialog.value = true }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.theme),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(10.dp)
                    .weight(1f)
                    .wrapContentSize(align = Alignment.CenterStart)
            )
            Text(
                text = themeName.value,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(10.dp)
                    .weight(1f)
                    .wrapContentSize(align = Alignment.CenterEnd)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ThemeChooseDialog(
    currentTheme: Theme = Theme.SYSTEM,
    onThemeChoose: (theme: Theme) -> Unit,
    onDismissButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissButtonClick,
        title = {
            Text(
                text = stringResource(R.string.choose_theme),
                style = MaterialTheme.typography.h5
            )
        },
        dismissButton = {
            Button(
                onClick = onDismissButtonClick,
            ) {
                Text(text = stringResource(R.string.close))
            }
        },
        confirmButton = {},
        text = {
            Column(modifier = Modifier.selectableGroup()) {
                for (theme in Theme.values()) {
                    Card(
                        onClick = { onThemeChoose(theme) },
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 0.dp,
                        border = BorderStroke(0.dp, Color.Transparent),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = currentTheme == theme,
                                enabled = true,
                                onClick = { onThemeChoose(theme) }
                            )
                            Text(
                                text = when (theme) {
                                    Theme.DARK -> stringResource(R.string.dark)
                                    Theme.LIGHT -> stringResource(R.string.light)
                                    else -> stringResource(R.string.system)
                                },
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun ThemePickerPreview() {
    ThemePicker(hiltViewModel())
}