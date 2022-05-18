package com.ateca.ui.screens.note_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ateca.ui.screens.note_screen.veiwmodel.NoteViewModel

@Composable
fun NoteContent(
    viewModel: NoteViewModel
) {
    val note = viewModel.note.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        NoteTitleField(title = note.value.title) { value ->
            viewModel.updateNote(title = value.trim())
        }
        NoteTextField(text = note.value.text) { value ->
            viewModel.updateNote(text = value.trim())
        }
    }
}

@Preview(backgroundColor = 0xFF00FF00, showBackground = true)
@Composable
private fun NoteContentPreview() {
    NoteContent(viewModel = hiltViewModel())
}

@Composable
private fun NoteTitleField(
    title: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = title,
        maxLines = 1,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.h6,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .border(BorderStroke(width = 0.dp, color = Color.Transparent))
    )
}

@Preview(backgroundColor = 0xFF00FF00, showBackground = true)
@Composable
private fun NoteTitlePreview() {
    NoteTitleField(title = "Title") {}
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
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    )
}

@Preview(backgroundColor = 0xFF00FF00, showBackground = true)
@Composable
private fun NoteTextPreview() {
    NoteTitleField(title = "Text") {}
}