package com.ateca.ui.screens.note_list.view.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing

/**
 * Created by dronpascal on 20.05.2022.
 */
@Composable
fun NoteList(
    noteItems: List<Note>,
    selectedIds: List<NoteId>,
    onNoteClicked: (Note) -> Unit,
    onNoteLongPress: (Note) -> Unit,
) {

    val lazyListState: LazyListState = rememberLazyListState()

    LazyColumn(state = lazyListState) {
        items(noteItems) { noteItem ->
            val isSelected: Boolean = selectedIds.contains(noteItem.id)
            NoteListItem(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
                noteItem = noteItem,
                isSelected = isSelected,
                onNoteClicked = onNoteClicked,
                onNoteLongPress = onNoteLongPress
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.tiny))
        }
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.huge))
        }
    }
}

@Preview(
    name = "ListLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "ListDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "SearchBarLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun NoteListPreview() {
    AtecaTheme {
        NoteList(
            noteItems = com.ateca.ui.screens.note_list.view.constants.NoteListPreviewConstants.items,
            selectedIds = com.ateca.ui.screens.note_list.view.constants.NoteListPreviewConstants.selectedIds,
            {}
        ) {}
    }
}