package com.ateca.ui.screens.settings.view.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
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
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing

@Composable
fun SettingsTile(
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
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
                .padding(vertical = 12.dp)
                .weight(1f)
                .wrapContentSize(align = Alignment.CenterStart)
        )
        Text(
            text = settingsValue,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(end = MaterialTheme.spacing.medium)
                .padding(vertical = 12.dp)
                .weight(1f)
                .wrapContentSize(align = Alignment.CenterEnd)
        )
    }
}

@Preview(
    name = "SettingsTileLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SettingsTileDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SettingsTileLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun SettingsTilePreview() {
    AtecaTheme {
        SettingsTile(
            settingsTitle = stringResource(R.string.theme),
            settingsValue = stringResource(R.string.dark),
            onClick = {}
        )
    }
}