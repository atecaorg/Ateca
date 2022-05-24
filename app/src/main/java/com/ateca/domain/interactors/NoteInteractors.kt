package com.ateca.domain.interactors

import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.entity.IMarkdownProcessor
import com.ateca.domain.interactors.note.delete.DeleteNote
import com.ateca.domain.interactors.note.delete.DeleteNotes
import com.ateca.domain.interactors.note.insert.CreateNote
import com.ateca.domain.interactors.note.select.GetAllNotes
import com.ateca.domain.interactors.note.select.GetNoteBacklinks
import com.ateca.domain.interactors.note.select.GetNoteById
import com.ateca.domain.interactors.note.select.GetNoteLinks
import com.ateca.domain.interactors.note.update.ArchiveNote
import com.ateca.domain.interactors.note.update.SaveNote

/**
 * Created by dronpascal on 18.05.2022.
 */
data class NoteInteractors(
    // select
    val getAllNotes: NoteInteractor.IGetAllNotes,
    val getNoteById: NoteInteractor.IGetNoteById,
    val getNoteLinks: NoteInteractor.IGetNoteLinks,
    val getNoteBacklinks: NoteInteractor.IGetNoteBacklinks,
    // insert
    val createNote: NoteInteractor.ICreateNote,
    // update
    val archiveNote: NoteInteractor.IArchiveNote,
    val saveNote: NoteInteractor.ISaveNote,
    // delete
    val deleteNote: NoteInteractor.IDeleteNote,
    val deleteNotes: NoteInteractor.IDeleteNotes
) {
    companion object Factory {
        fun build(
            noteDataSource: INoteDataSource,
            linkDataSource: ILinkDataSource,
            markdownProcessor: IMarkdownProcessor
        ): NoteInteractors =
            NoteInteractors(
                getAllNotes = GetAllNotes(noteDataSource),
                getNoteById = GetNoteById(noteDataSource),
                getNoteLinks = GetNoteLinks(linkDataSource),
                getNoteBacklinks = GetNoteBacklinks(linkDataSource),
                createNote = CreateNote(noteDataSource),
                archiveNote = ArchiveNote(noteDataSource),
                saveNote = SaveNote(noteDataSource, markdownProcessor),
                deleteNote = DeleteNote(noteDataSource),
                deleteNotes = DeleteNotes(noteDataSource)
            )
    }
}