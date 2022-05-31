package com.ateca.ui.screens.note_screen

import android.content.res.Configuration
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.domain.models.Note
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.theme.md2.AtecaTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NoteScreen(
    note: StateFlow<Note>,
    onNavigationButtonClick: () -> Unit,
    onNoteUpdate: (
        title: String,
        text: String,
        folder: String?,
        tags: List<String>
    ) -> Unit
) {

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = note.collectAsState().value.title,
                onNavigationButtonClick = onNavigationButtonClick,
                onTitleValueChange = { value ->
                    onNoteUpdate(
                        value,
                        note.value.text,
                        note.value.folder,
                        note.value.tags
                    )
                }
            )
        }
    ) { paddingValues ->
        NoteContent(
            paddingValues = paddingValues,
            noteState = note,
            onNoteUpdate = onNoteUpdate
        )
    }
}

@Preview(
    name = "NoteScreenLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Preview(
    name = "NoteScreenDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Composable
private fun NoteScreenPreview() {
    AtecaTheme {
        NoteScreen(
            note = MutableStateFlow(
                Note(
                    title = "SpaceX",
                    text = "Space Exploration Technologies Corp. is an American space manufacturer, a provider of space transportation services, and a communications corporation headquartered in Hawthorne, California. SpaceX was founded in 2002 by Elon Musk with the goal of reducing space transportation costs to enable the colonization of Mars."
                )
            ),
            onNavigationButtonClick = {},
            onNoteUpdate = { _, _, _, _ -> }
        )
    }
}