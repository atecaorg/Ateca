package com.ateca.domain.datasource

import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId

interface INoteDataSource {

    fun getAllNotes(): List<Note>

    fun deleteNoteById(id: NoteId)

    fun saveNote(note: Note)

    fun setArchived(id: NoteId, isArchived: Boolean)
}