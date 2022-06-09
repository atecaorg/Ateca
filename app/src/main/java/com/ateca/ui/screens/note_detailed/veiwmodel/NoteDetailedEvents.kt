package com.ateca.ui.screens.note_detailed.veiwmodel

/**
 * Created by dronpascal on 09.06.2022.
 */
sealed class NoteDetailedEvents {

    object RemoveHeadFromMessageQueue : NoteDetailedEvents()

    data class GetNoteById(val noteId: String) : NoteDetailedEvents()

    data class UpdateText(val text: String) : NoteDetailedEvents()

    data class UpdateTitle(val title: String) : NoteDetailedEvents()

    object SaveNote : NoteDetailedEvents()

    object ChangeUIMode : NoteDetailedEvents()
}