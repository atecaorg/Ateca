package com.ateca.ui.components.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing

/**
 * Created by dronpascal on 21.05.2022.
 */
@Composable
fun StubScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
            .wrapContentSize()
            .padding(MaterialTheme.spacing.large)
    ) {
        Image(
            painterResource(R.drawable.not_found_stub),
            contentDescription = null
        )
        Spacer(Modifier.height(MaterialTheme.spacing.large))
        Text(
            text = stringResource(R.string.work_in_progress),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = stringResource(R.string.grab_beverage),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(
    name = "StubScreenLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "StubScreenDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "StubScreenLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun StubScreenPreview() {
    AtecaTheme {
        StubScreen()
    }
}
