package com.ateca.ui.screens.settings.view.components

import android.content.res.Configuration
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
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing

@Composable
fun SettingsGroup(
    text: String,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = MaterialTheme.spacing.medium
    ),
    textColor: Color = MaterialTheme.colors.primary,
    style: TextStyle = MaterialTheme.typography.body1
) {
    Text(
        text = text,
        color = textColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(vertical = MaterialTheme.spacing.small),
        style = style
    )
}

@Preview(
    name = "SettingsGroupLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SettingsGroupDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SettingsGroupLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun SettingsGroupPreview() {
    AtecaTheme {
        SettingsGroup(
            text = stringResource(R.string.settings)
        )
    }
}