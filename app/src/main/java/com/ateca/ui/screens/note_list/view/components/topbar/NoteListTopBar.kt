package com.ateca.ui.screens.note_list.view.components.topbar

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.BuildConfig
import com.ateca.R
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.icon.ThemedIcon
import com.ateca.ui.components.topappbar.NoteAppTopBar
import com.ateca.ui.screens.note_list.view.constants.NoteListPreviewConstants
import com.ateca.ui.theme.md2.AtecaTheme

/**
 * Created by dronpascal on 20.05.2022.
 */
@Composable
fun NoteListTopBar(
    selectedIds: List<NoteId>,
    selectedNote: Note?,
    isScrollInInitialState: (() -> Boolean)?,
    onSettingIconClicked: () -> Unit,
    onAddTestNoteClicked: () -> Unit,
    onCloseSelectModeClicked: () -> Unit,
    onSelectAllClicked: () -> Unit,
) {
    if (selectedIds.isEmpty()) {
        val title = selectedNote?.title ?: stringResource(R.string.app_name)
        NoteAppTopBar(
            title = title,
            isScrollInInitialState = isScrollInInitialState,
            actions = {
                if (BuildConfig.DEBUG) {
                    IconButton(
                        modifier = Modifier.background(color = Color.Magenta),
                        onClick = { onAddTestNoteClicked() }) {
                        ThemedIcon(
                            Icons.Filled.Add,
                            contentDescription = "Add test note"
                        )
                    }
                }
                IconButton(onClick = { onSettingIconClicked() }) {
                    ThemedIcon(
                        Icons.Filled.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        )
    } else {
        EditModeTopBar(
            selectedCount = selectedIds.size,
            onCloseSelectModeClicked = onCloseSelectModeClicked,
            onSelectAllClicked = onSelectAllClicked,
            isScrollInInitialState = isScrollInInitialState
        )
    }
}

@Preview(
    name = "NoteListTopBarLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteListTopBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SearchBarLargeFont",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    fontScale = PREVIEW_FONT_SCALE
)
@Composable
private fun NoteListTopBarPreview() {
    AtecaTheme {
        NoteListTopBar(
            emptyList(),
            null,
            null,
            {},
            {},
            {},
            {},
        )
    }
}

@Preview(
    name = "NoteListTopBarEditModeLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteListTopBarEditModeDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SearchBarLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun NoteListTopBarEditModePreview() {
    AtecaTheme {
        NoteListTopBar(
            NoteListPreviewConstants.selectedIds,
            null,
            null,
            {},
            {},
            {},
            {},
        )
    }
}