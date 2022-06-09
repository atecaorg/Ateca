package com.ateca.ui.navigation.destinations

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ateca.domain.constants.NavigationConstants
import com.ateca.ui.navigation.Animations.enterTransition
import com.ateca.ui.navigation.Animations.exitTransition
import com.ateca.ui.navigation.Screen
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteDetailedViewModel
import com.ateca.ui.screens.note_detailed.view.NoteDetailed
import com.google.accompanist.navigation.animation.composable

/**
 * Modified by dronpascal on 21.05.2022.
 */
@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addNoteDetailed(
    navController: NavHostController,
) {
    composable(
        route = Screen.Note.route,
        arguments = listOf(navArgument(NavigationConstants.NOTE_ID_ARGUMENT_KEY) {
            type = NavType.StringType
        }),
        enterTransition = {
            enterTransition
        },
        exitTransition = {
            exitTransition
        }
    ) {
        val viewModel: NoteDetailedViewModel = hiltViewModel()
        NoteDetailed(
            state = viewModel.state.value,
            events = viewModel::onTriggerEvent,
            onNavigateBack = { navController.popBackStack() }
        )
    }
}