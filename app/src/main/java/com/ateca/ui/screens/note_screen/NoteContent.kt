package com.ateca.ui.screens.note_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ateca.ui.screens.note_screen.veiwmodel.NoteViewModel

@Composable
fun NoteContent(
    viewModel: NoteViewModel
) {
    val note = viewModel.note.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .border(BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSurface))
    ) { }
}

@Preview
@Composable
private fun NoteContentPreview() {
    NoteContent(viewModel = hiltViewModel())
}