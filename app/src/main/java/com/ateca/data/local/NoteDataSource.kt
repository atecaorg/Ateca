package com.ateca.data.local

import com.ateca.data.local.room.dao.NoteDao
import com.ateca.data.local.room.toEntity
import com.ateca.data.local.room.toModel
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId

/**
 * Created by dronpascal on 16.05.2022.
 */
class NoteDataSource(
    private val noteDao: NoteDao
) : INoteDataSource {

    override fun getAllNotes(): List<Note> =
        noteDao.selectAll().toModel()

    override fun deleteNoteById(id: NoteId) {
        noteDao.deleteByNoteId(id)
    }

    override fun saveNote(note: Note) {
        noteDao.insert(note.toEntity())
    }

    override fun setArchived(id: NoteId, isArchived: Boolean) {
        noteDao.setArchived(id, isArchived)
    }
}