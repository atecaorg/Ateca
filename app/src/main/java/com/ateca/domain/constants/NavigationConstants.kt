package com.ateca.domain.constants

/**
 * Created by dronpascal on 20.05.2022.
 */
object NavigationConstants {

    const val NOTE_ID_ARGUMENT_KEY = "noteId"

    object Rotes {
        const val STUB_ROUTE = "stub"
        const val NOTE_LIST_ROUTE = "note_list"
        private const val NOTE_URI = "note_detailed"
        const val NOTE_ROUTE = "$NOTE_URI/{$NOTE_ID_ARGUMENT_KEY}"
        const val NOTE_ROUTE_TEMPLATE = "$NOTE_URI/%s"
        const val SETTINGS_ROUTE = "settings"
    }
}
