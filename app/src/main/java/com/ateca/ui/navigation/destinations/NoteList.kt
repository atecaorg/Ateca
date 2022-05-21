package com.ateca.ui.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ateca.domain.constants.NavigationConstants.Rotes.STUB_ROUTE
import com.ateca.ui.navigation.Animations
import com.ateca.ui.navigation.Screen
import com.ateca.ui.screens.note_list.view.NoteListScreen
import com.ateca.ui.screens.note_list.viewmodel.NoteListViewModel
import com.google.accompanist.navigation.animation.composable

/**
 * Created by dronpascal on 21.05.2022.
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addNoteList(
    navController: NavHostController
) {
    composable(
        route = Screen.NoteList.route,
        enterTransition = {
            Animations.enterTransition
        },
        exitTransition = {
            Animations.exitTransition
        }
    ) {
        val viewModel: NoteListViewModel = hiltViewModel()
        NoteListScreen(
            state = viewModel.state.value,
            events = viewModel::onTriggerEvent,
            onNavigateToSettingsScreen = {},
            onNavigateToNoteDetailed = {
                navController.navigate(STUB_ROUTE)
            }
//            { noteId ->
//                navController.navigate(
//                    NOTE_ROUTE_TEMPLATE.format(noteId)
//                )
//            }
        )
    }
}