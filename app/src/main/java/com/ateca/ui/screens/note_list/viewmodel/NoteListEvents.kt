package com.ateca.ui.screens.note_list.viewmodel

import com.ateca.domain.models.Note

/**
 * Created by dronpascal on 21.05.2022.
 */
sealed class NoteListEvents {

    object GetAllNotes : NoteListEvents()

    object OnRemoveHeadFromQueue : NoteListEvents()

    object DeleteSelected : NoteListEvents()

    object SelectAll : NoteListEvents()

    object UnselectAll : NoteListEvents()

    data class OnNoteLongPress(val note: Note) : NoteListEvents()

    object OnAddTestNoteClicked : NoteListEvents()
}