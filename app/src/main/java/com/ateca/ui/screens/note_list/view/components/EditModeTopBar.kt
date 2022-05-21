package com.ateca.ui.screens.note_list.view.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.R
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
    onCloseEditModeClicked: () -> Unit,
    onDeleteSelectedClicked: () -> Unit,
    onSelectAllClicked: () -> Unit,
    isScrollInInitialState: (() -> Boolean)? = null,
) {
    val expanded = remember { mutableStateOf(false) }
    ScrollAwareTopAppBar(
        title = { Text(text = selectedCount.toString()) },
        navigationIcon = {
            IconButton(onClick = onCloseEditModeClicked) {
                ThemedIcon(Icons.Filled.Clear, contentDescription = "Close")
            }
        },
        actions = {
            IconButton(onClick = onDeleteSelectedClicked) {
                ThemedIcon(
                    Icons.Filled.Delete,
                    contentDescription = "Delete"
                )
            }
            Box(Modifier.wrapContentSize(Alignment.TopEnd)) {
                IconButton(onClick = {
                    expanded.value = true
                }) {
                    ThemedIcon(
                        Icons.Filled.MoreVert,
                        contentDescription = "Menu"
                    )
                }

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                ) {
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        onSelectAllClicked()
                    }) {
                        Text(stringResource(R.string.select_all))
                    }
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
        EditModeTopBar(3, { }, { }, { })
    }
}