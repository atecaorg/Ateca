package com.ateca.ui.screens.settings.view.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants

@Composable
fun SettingsTitleText(
    text: String,
    paddingValues: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp),
    textColor: Color = MaterialTheme.colors.error,
    style: TextStyle = MaterialTheme.typography.body1
) {
    Text(
        text = text,
        color = textColor,
        modifier = Modifier.fillMaxWidth()
            .padding(paddingValues),
        style = style
    )
}

@Preview(
    name = "SettingsTitleTextLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun SettingsTitleTextLightPrewiew() {
    SettingsTitleText(
        text = stringResource(R.string.settings)
    )
}

@Preview(
    name = "SettingsTitleTextDark",
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun SettingsTitleTextDarkPrewiew() {
    SettingsTitleText(
        text = stringResource(R.string.settings)
    )
}