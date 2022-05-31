package com.ateca.ui.screens.note_list.viewmodel

import com.ateca.domain.models.Note

/**
 * Created by dronpascal on 21.05.2022.
 */
sealed class NoteListEvents {

    object OnRemoveHeadFromQueue : NoteListEvents()

    data class OnNoteLongPress(val note: Note) : NoteListEvents()

    object SelectAll : NoteListEvents()

    object UnselectAll : NoteListEvents()

    object DeleteSelected : NoteListEvents()

    data class OnQueryChanged(val query: String) : NoteListEvents()

    object OnAddTestNoteClicked : NoteListEvents()
}