package com.ateca.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ateca.domain.constants.NavigationConstants.NOTE_ID_ARGUMENT_KEY
import com.ateca.domain.constants.NavigationConstants.Rotes.NOTE_LIST_ROUTE
import com.ateca.domain.constants.NavigationConstants.Rotes.NOTE_ROUTE
import com.ateca.domain.constants.NavigationConstants.Rotes.SETTINGS_ROUTE
import com.ateca.domain.constants.NavigationConstants.Rotes.STUB_ROUTE

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object StubScreen : Screen(
        route = STUB_ROUTE,
        arguments = emptyList()
    )

    object NoteList : Screen(
        route = NOTE_LIST_ROUTE,
        arguments = emptyList()
    )

    object Note : Screen(
        route = NOTE_ROUTE,
        arguments = listOf(
            navArgument(NOTE_ID_ARGUMENT_KEY) {
                type = NavType.StringType
            }
        )
    )

    object Settings : Screen(
        route = SETTINGS_ROUTE,
        arguments = emptyList()
    )
}