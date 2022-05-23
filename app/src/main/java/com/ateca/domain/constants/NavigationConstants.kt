package com.ateca.domain.constants

/**
 * Created by dronpascal on 20.05.2022.
 */
object NavigationConstants {

    const val NOTE_ID_ARGUMENT_KEY = "noteId"

    object Rotes {
        const val STUB_ROUTE = "stub"
        const val NOTE_LIST_ROUTE = "note_list"
        const val NOTE_ROUTE = "note_detailed"
        const val NOTE_ROUTE_TEMPLATE = "$NOTE_ROUTE/{$NOTE_ID_ARGUMENT_KEY}"
        const val SETTINGS_ROUTE = "settings"
    }
}
