package com.ateca.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ateca.domain.util.NOTE_ID_ARGUMENT_KEY
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    val duration = 700
    val delay = 400
    val enterTransition = fadeIn(
        animationSpec = tween(durationMillis = duration, delayMillis = delay)
    )
    val exitTransition = fadeOut(
        animationSpec = tween(durationMillis = duration, delayMillis = delay)
    )

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route,
            enterTransition = {
                enterTransition
            },
            exitTransition = {
                exitTransition
            }
        ) {}
        composable(
            route = Screen.NoteScreen.route,
            arguments = listOf(navArgument(NOTE_ID_ARGUMENT_KEY) {
                type = NavType.StringType
            }),
            enterTransition = {
                enterTransition
            },
            exitTransition = {
                exitTransition
            }
        ) {}
        composable(
            route = Screen.SettingsScreen.route,
            enterTransition = {
                enterTransition
            },
            exitTransition = {
                exitTransition
            }
        ) {}
    }
}