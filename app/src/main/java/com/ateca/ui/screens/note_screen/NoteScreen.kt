package com.ateca.ui.screens.note_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ateca.ui.screens.note_screen.veiwmodel.NoteViewModel

@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    Scaffold { }
}