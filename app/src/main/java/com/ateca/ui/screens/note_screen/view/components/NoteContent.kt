package com.ateca.ui.screens.note_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ateca.domain.models.Note
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.theme.md2.AtecaTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NoteContent(
    paddingValues: PaddingValues = PaddingValues(8.dp),
    noteState: StateFlow<Note>,
    onNoteUpdate: (
        title: String,
        text: String,
        folder: String?,
        tags: List<String>
    ) -> Unit
) {
    val note = noteState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        NoteTextField(text = note.value.text) { value ->
            onNoteUpdate(
                note.value.title,
                value,
                note.value.folder,
                note.value.tags
            )
        }
    }
}

@Composable
private fun NoteTextField(
    text: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            textColor = MaterialTheme.colors.onBackground
        ),
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview(
    name = "NoteContentLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteContentDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun NoteContentPreview() {
    AtecaTheme {
        NoteContent(noteState = MutableStateFlow(
            Note(
                title = "SpaceX",
                text = "Space Exploration Technologies Corp. is an American space manufacturer, a provider of space transportation services, and a communications corporation headquartered in Hawthorne, California. SpaceX was founded in 2002 by Elon Musk with the goal of reducing space transportation costs to enable the colonization of Mars."
            )
        )) { _, _, _, _ ->
        }
    }
}

@Preview(
    name = "NoteTextLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteTextDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun NoteTextPreview() {
    AtecaTheme {
        NoteTextField(text = "Text") {}
    }
}