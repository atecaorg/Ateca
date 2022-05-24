package com.ateca.domain.datasource

import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId

interface INoteDataSource {

    suspend fun selectAll(): List<Note>

    suspend fun select(id: NoteId): Note

    suspend fun setArchived(id: NoteId, isArchived: Boolean)

    suspend fun selectBaseTitles(): List<String>

    suspend fun getIdByTitle(title: String): NoteId

    suspend fun saveNote(note: Note)

    suspend fun deleteNoteById(id: NoteId)

    suspend fun deleteNotesByIds(ids: List<NoteId>)
}