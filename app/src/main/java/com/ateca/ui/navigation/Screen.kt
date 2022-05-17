package com.ateca.ui.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = HOME_ROUTE)
    object NoteScreen : Screen(route = "$NOTE_ROUTE/{noteID}") {
        fun passNoteId(noteId: String) = "$NOTE_ROUTE/$noteId"
    }

    object SettingsScreen : Screen(route = SETTINGS_ROUTE)

    companion object {
        private const val HOME_ROUTE = "home_route"
        private const val NOTE_ROUTE = "note_route"
        private const val SETTINGS_ROUTE = "settings_route"
    }
}
