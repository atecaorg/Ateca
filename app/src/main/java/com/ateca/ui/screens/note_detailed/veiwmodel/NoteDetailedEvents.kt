package com.ateca.ui.screens.note_detailed.veiwmodel

import androidx.compose.ui.text.input.TextFieldValue

/**
 * Created by dronpascal on 09.06.2022.
 */
sealed class NoteDetailedEvents {

    object RemoveHeadFromMessageQueue : NoteDetailedEvents()

    data class GetNoteById(val noteId: String) : NoteDetailedEvents()

    data class UpdateText(val textValue: TextFieldValue) : NoteDetailedEvents()

    data class UpdateTitle(val title: String) : NoteDetailedEvents()

    object SaveNote : NoteDetailedEvents()

    object ChangeUIMode : NoteDetailedEvents()
}