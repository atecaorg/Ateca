package com.ateca.ui.screens.note_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ateca.ui.screens.note_screen.veiwmodel.NoteViewModel

@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = viewModel.note.collectAsState().value.title,
                onNavigationButtonClick = { navController.popBackStack() },
                onTitleValueChange = { value ->
                    viewModel.updateNote(title = value)
                }
            )
        }
    ) { paddingValues ->
        NoteContent(
            paddingValues = paddingValues,
            viewModel = viewModel
        )
    }
}