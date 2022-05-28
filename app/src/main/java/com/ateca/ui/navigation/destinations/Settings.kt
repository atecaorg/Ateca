package com.ateca.ui.navigation.destinations


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ateca.ui.navigation.Animations
import com.ateca.ui.navigation.Screen
import com.ateca.ui.screens.settings.view.SettingsScreen
import com.ateca.ui.screens.settings.viewmodel.SettingsViewModel
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addSettings(
    navController: NavHostController
) {
    composable(
        route = Screen.Settings.route,
        enterTransition = { Animations.enterTransition },
        exitTransition = { Animations.exitTransition }

    ) {
        val viewModel: SettingsViewModel = hiltViewModel()
        SettingsScreen(
            navController = navController,
            getSettingsFlow = { viewModel.settings },
            setThemeSetting = { viewModel.setThemeSetting(it) })
    }
}

