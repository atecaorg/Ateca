package com.ateca.ui.components.topappbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.icon.ThemedIcon
import com.ateca.ui.theme.md2.AtecaTheme

/**
 * Created by dronpascal on 20.05.2022.
 */
@Composable
fun NoteAppTopBar(
    title: String = "",
    withBackIcon: Boolean = false,
    onBackPressed: (() -> Unit)? = null,
    isScrollInInitialState: (() -> Boolean)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {

    val navIcon: @Composable (() -> Unit)? =
        if (withBackIcon) {
            {
                IconButton(
                    onClick = { onBackPressed?.invoke() }
                ) {
                    ThemedIcon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        } else {
            null
        }

    ScrollAwareTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = navIcon,
        actions = actions,
        isScrollInInitialState = isScrollInInitialState
    )
}

@Preview(
    name = "NoteAppTopBarLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteAppTopBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteAppTopBarLargeFont",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    fontScale = PREVIEW_FONT_SCALE
)
@Composable
private fun NoteAppTopBarPreview() {
    AtecaTheme {
        NoteAppTopBar(
            withBackIcon = true,
            title = AppPreviewConstants.title
        )
    }
}