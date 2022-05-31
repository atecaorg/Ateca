package com.ateca.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ateca.ui.navigation.destinations.addNoteDetailed
import com.ateca.ui.navigation.destinations.addNoteList
import com.ateca.ui.navigation.destinations.addSettings
import com.ateca.ui.navigation.destinations.addStub
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.NoteList.route,
        modifier = modifier
    ) {
        addNoteList(navController = navController)
        addNoteDetailed(navController = navController)
        addStub()
        addSettings(navController = navController)
    }
}