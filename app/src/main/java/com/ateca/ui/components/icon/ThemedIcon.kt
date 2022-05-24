package com.ateca.ui.components.icon

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.theme.md2.AtecaTheme

/**
 * Created by dronpascal on 20.05.2022.
 */
const val ICON_MIN_SCALE = 1f
const val ICON_MAX_SCALE = 1.4f

@Composable
fun ThemedIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onSurface
) {
    val fontScale = LocalDensity.current.fontScale
    val scaleValue = when {
        fontScale > ICON_MAX_SCALE -> ICON_MAX_SCALE
        fontScale > ICON_MIN_SCALE -> fontScale
        else -> ICON_MIN_SCALE
    }
    Icon(imageVector, contentDescription, modifier.scale(scaleValue), tint)
}

@Composable
fun ThemedIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onSurface
) {
    val fontScale = LocalDensity.current.fontScale
    val scaleValue = when {
        fontScale > 1.4f -> 1.4f
        fontScale > 1f -> fontScale
        else -> 1f
    }
    Icon(painter, contentDescription, modifier.scale(scaleValue), tint)
}

@Preview(
    name = "ThemedIconLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "ThemedIconDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "StubScreenLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun ThemedIconPreview() {
    AtecaTheme {
        ThemedIcon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}