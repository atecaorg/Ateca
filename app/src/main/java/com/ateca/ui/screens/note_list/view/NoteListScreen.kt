package com.ateca.ui.screens.note_list.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.domain.models.Note
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.dialog.StubPopup
import com.ateca.ui.components.screen.DefaultScreenUI
import com.ateca.ui.screens.note_list.view.components.NoteList
import com.ateca.ui.screens.note_list.view.components.NoteListTopBar
import com.ateca.ui.screens.note_list.view.components.search.SearchBar
import com.ateca.ui.screens.note_list.view.components.search.SearchState
import com.ateca.ui.screens.note_list.view.components.search.rememberSearchState
import com.ateca.ui.screens.note_list.view.constants.NoteListPreviewConstants
import com.ateca.ui.screens.note_list.viewmodel.NoteListEvents
import com.ateca.ui.screens.note_list.viewmodel.NoteListState
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.util.isScrollInInitialState

/**
 * Created by dronpascal on 19.05.2022.
 */
@Composable
fun NoteListScreen(
    state: NoteListState,
    events: (NoteListEvents) -> Unit = {},
    onNavigateToSettingsScreen: () -> Unit = {},
    onNavigateToNoteDetailed: (String) -> Unit = {},
) {

    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(NoteListEvents.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
    ) {
        val lazyListState: LazyListState = rememberLazyListState()

        var stubPopupShown by remember { mutableStateOf(false) }
        if (stubPopupShown) {
            StubPopup { stubPopupShown = false }
        }

        Scaffold(
            modifier = Modifier,
            topBar = {
                NoteListTopBar(
                    selectedIds = state.selectedIds,
                    selectedNote = state.selectedNote,
                    onDeleteSelectedClicked = { events(NoteListEvents.DeleteSelected) },
                    onSettingIconClicked = { onNavigateToSettingsScreen() },
                    onAddTestNoteClicked = { events(NoteListEvents.OnAddTestNoteClicked) },
                    isScrollInInitialState = { lazyListState.isScrollInInitialState() }
                )
            },
            content = { paddings ->
                val onNoteClicked: (Note) -> Unit = { note ->
                    if (state.selectedIds.isEmpty()) {
                        onNavigateToNoteDetailed(note.id)
                    } else {
                        events(NoteListEvents.OnNoteLongPress(note))
                    }
                }

                val searchState: SearchState = rememberSearchState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddings)
                ) {
                    SearchBar(
                        query = searchState.query,
                        onQueryChange = { searchState.query = it },
                        searchFocused = searchState.focused,
                        onSearchFocusChange = { searchState.focused = it },
                        onClearQuery = { searchState.query = TextFieldValue("") },
                        searching = searchState.searching
                    )
                    NoteList(
                        noteItems = state.noteItems,
                        selectedIds = state.selectedIds,
                        onNoteClicked = onNoteClicked,
                        onNoteLongPress = { note -> NoteListEvents.OnNoteLongPress(note) }
                    )
                }
            },
        )
    }
}

@Preview(
    name = "NoteListScreenLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteListScreenDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteListScreenLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun NoteListScreenPreview() {
    AtecaTheme {
        NoteListScreen(state = NoteListPreviewConstants.state)
    }
}