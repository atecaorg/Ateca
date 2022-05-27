package com.ateca.ui.screens.settings.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ateca.R
import com.ateca.domain.entity.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ThemeSettingsDialog(
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
            TextButton(
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