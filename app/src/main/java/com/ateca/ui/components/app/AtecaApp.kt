package com.ateca.ui.components.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ateca.ui.navigation.SetUpNavGraph
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AtecaApp() {

    val navController = rememberAnimatedNavController()
    SetUpNavGraph(
        navController = navController,
        modifier = Modifier.background(color = MaterialTheme.colors.background)
    )
}
