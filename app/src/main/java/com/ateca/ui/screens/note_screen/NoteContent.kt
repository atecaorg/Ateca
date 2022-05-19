package com.ateca.ui.screens.note_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .padding(10.dp)
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colors.onPrimary
                ), shape = RoundedCornerShape(10.dp)
            )
    ) {
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
            focusedBorderColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxSize()
    )
}

@Preview(backgroundColor = 0xFF00FF00, showBackground = true)
@Composable
private fun NoteTextPreview() {
    NoteTextField(text = "Text") {}
}