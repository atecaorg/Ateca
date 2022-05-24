package com.ateca.ui.screens.note_list.view.components.topbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.icon.ThemedIcon
import com.ateca.ui.components.topappbar.ScrollAwareTopAppBar
import com.ateca.ui.theme.md2.AtecaTheme

/**
 * Created by dronpascal on 20.05.2022.
 */
@Composable
fun EditModeTopBar(
    selectedCount: Int,
    onCloseSelectModeClicked: () -> Unit,
    onSelectAllClicked: () -> Unit,
    isScrollInInitialState: (() -> Boolean)? = null,
) {
    ScrollAwareTopAppBar(
        title = {
            val titleText = when (selectedCount > 0) {
                true -> stringResource(
                    id = R.string.note_selected_count_template,
                    selectedCount.toString()
                )
                false -> stringResource(id = R.string.select_objects)
            }
            Text(
                text = titleText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onCloseSelectModeClicked) {
                ThemedIcon(Icons.Filled.Clear, contentDescription = "Close")
            }
        },
        actions = {
            Box(Modifier.wrapContentSize(Alignment.TopEnd)) {
                IconButton(onClick = onSelectAllClicked) {
                    ThemedIcon(
                        Icons.Filled.Checklist,
                        contentDescription = "Select all"
                    )
                }
            }

        },
        isScrollInInitialState = isScrollInInitialState
    )
}

@Preview(
    name = "EditModeTopBarLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "EditModeTopBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SearchBarLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun EditModeTopBarPreview() {
    AtecaTheme {
        EditModeTopBar(3, { }, { })
    }
}