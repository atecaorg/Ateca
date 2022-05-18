package com.ateca.ui.screens.note_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ateca.ui.screens.note_screen.veiwmodel.NoteViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun NoteScreen(
    navController: NavHostController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val systemPaddingValues = rememberInsetsPaddingValues(
        insets = LocalWindowInsets.current.systemBars
    )

    Scaffold(
        topBar = {
            NoteTopAppBar(
                paddingValues = systemPaddingValues,
                title = viewModel.note.collectAsState().value.title,
                onNavigationButtonClick = { navController.popBackStack() },
                onSettingButtonClick = {}
            )
        }
    ) {
        NoteContent(viewModel = viewModel)
    }
}