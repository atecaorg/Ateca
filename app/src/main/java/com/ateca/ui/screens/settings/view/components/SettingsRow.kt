package com.ateca.ui.screens.settings.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants

@Composable
fun SettingsRow(
    settingsTitle: String,
    settingsValue: String = "",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Text(
            text = settingsTitle,
            modifier = Modifier
                .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
                .weight(1f)
                .wrapContentSize(align = Alignment.CenterStart),
            style = MaterialTheme.typography.body1
        )
        Text(
            text = settingsValue,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp, end = 16.dp)
                .weight(1f)
                .wrapContentSize(align = Alignment.CenterEnd)
        )
    }
}

@Preview(
    name = "SettingsRowLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun SettingsRowLightPrewiew() {
    SettingsRow(
        settingsTitle = stringResource(R.string.theme),
        settingsValue = stringResource(R.string.dark)
    ) {}
}

@Preview(
    name = "SettingsRowDark",
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun SettingsRowDarkPrewiew() {
    SettingsRow(
        settingsTitle = stringResource(R.string.theme),
        settingsValue = stringResource(R.string.dark)
    ) {}
}