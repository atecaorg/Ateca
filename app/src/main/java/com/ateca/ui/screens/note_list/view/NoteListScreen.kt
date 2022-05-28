package com.ateca.ui.screens.note_list.view

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.domain.constants.NoteConstants
import com.ateca.domain.models.Note
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.dialog.StubPopup
import com.ateca.ui.components.screen.DefaultScreenUI
import com.ateca.ui.screens.note_list.view.components.NewNoteFAB
import com.ateca.ui.screens.note_list.view.components.NoteDeleteDialog
import com.ateca.ui.screens.note_list.view.components.NoteList
import com.ateca.ui.screens.note_list.view.components.NoteListBottomBar
import com.ateca.ui.screens.note_list.view.components.search.SearchBar
import com.ateca.ui.screens.note_list.view.components.search.SearchState
import com.ateca.ui.screens.note_list.view.components.search.rememberSearchState
import com.ateca.ui.screens.note_list.view.components.topbar.NoteListTopBar
import com.ateca.ui.screens.note_list.view.constants.NoteListPreviewConstants
import com.ateca.ui.screens.note_list.viewmodel.NoteListEvents
import com.ateca.ui.screens.note_list.viewmodel.NoteListState
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.util.isScrollInInitialState

/**
 * Created by dronpascal on 19.05.2022.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteListScreen(
    state: NoteListState,
    events: (NoteListEvents) -> Unit = {},
    onNavigateToSettingsScreen: () -> Unit = {},
    onNavigateToNoteDetailed: (String) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val handleBackPressed: () -> Unit = {
        keyboardController?.hide()
        if (state.selectedIds.isNotEmpty()) {
            events(NoteListEvents.UnselectAll)
        } else {
            onBackPressed()
        }
    }
    BackHandler { handleBackPressed() }

    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(NoteListEvents.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
    ) {
        val lazyListState: LazyListState = rememberLazyListState()
        val isInSelectMode = state.selectedIds.isNotEmpty()

        val showStubPopup = rememberSaveable { mutableStateOf(false) }
        if (showStubPopup.value) StubPopup(state = showStubPopup)

        val showDeleteConfirmDialog = rememberSaveable { mutableStateOf(false) }
        if (showDeleteConfirmDialog.value) NoteDeleteDialog(
            state = showDeleteConfirmDialog,
            onConfirmClicked = { events(NoteListEvents.DeleteSelected) }
        )

        Scaffold(
            modifier = Modifier,
            topBar = {
                NoteListTopBar(
                    selectedIds = state.selectedIds,
                    selectedNote = state.selectedNote,
                    isScrollInInitialState = { lazyListState.isScrollInInitialState() },
                    onSettingIconClicked = onNavigateToSettingsScreen,
                    onAddTestNoteClicked = { events(NoteListEvents.OnAddTestNoteClicked) },
                    onCloseSelectModeClicked = { events(NoteListEvents.UnselectAll) },
                    onSelectAllClicked = { events(NoteListEvents.SelectAll) }
                )
            },
            content = { paddings ->
                val onNoteClicked: (Note) -> Unit = { note ->
                    when (isInSelectMode) {
                        true -> events(NoteListEvents.OnNoteLongPress(note))
                        false -> onNavigateToNoteDetailed(note.id)
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
                        onNoteLongPress = { note -> events(NoteListEvents.OnNoteLongPress(note)) }
                    )
                }
            },
            floatingActionButton = {
                if (!isInSelectMode) {
                    NewNoteFAB { onNavigateToNoteDetailed(NoteConstants.BLANK_NOTE_ID) }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                if (isInSelectMode) {
                    NoteListBottomBar(
                        onDeleteClicked = { showDeleteConfirmDialog.value = true }
                    )
                }
            }
        )
    }
}


@Preview(
    name = "NoteListScreenLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Composable
private fun NoteListScreenPreview() {
    AtecaTheme {
        NoteListScreen(
            state = NoteListPreviewConstants.state
                .copy(selectedIds = emptyList())
        )
    }
}

@Preview(
    name = "SelectedNoteListScreenLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Preview(
    name = "SelectedNoteListScreenDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Preview(
    name = "SelectedNoteListScreenLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Composable
private fun SelectedNoteListScreenPreview() {
    AtecaTheme {
        NoteListScreen(state = NoteListPreviewConstants.state)
    }
}

