package com.ateca.ui.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.ateca.ui.components.screen.StubScreen
import com.ateca.ui.navigation.Animations
import com.ateca.ui.navigation.Screen
import com.google.accompanist.navigation.animation.composable

/**
 * Created by dronpascal on 21.05.2022.
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addStub() {
    composable(
        route = Screen.StubScreen.route,
        enterTransition = {
            Animations.enterTransition
        },
        exitTransition = {
            Animations.exitTransition
        }
    ) {
        StubScreen()
    }
}