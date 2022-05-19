package com.ateca.ui.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.ateca.ui.navigation.SetUpNavGraph
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AtecaApp() {
    val navController = rememberAnimatedNavController()

    SetUpNavGraph(
        navController = navController
    )
}
