package com.ateca.domain.interactors

import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.entity.IMarkdownProcessor
import com.ateca.domain.interactors.note.FilterNotes
import com.ateca.domain.interactors.note.delete.DeleteNote
import com.ateca.domain.interactors.note.delete.DeleteNotes
import com.ateca.domain.interactors.note.insert.CreateNote
import com.ateca.domain.interactors.note.select.*
import com.ateca.domain.interactors.note.update.ArchiveNote
import com.ateca.domain.interactors.note.update.SaveNote

/**
 * Created by dronpascal on 18.05.2022.
 */
data class NoteInteractors(
    // Select
    val getAllNotes: IGetAllNotes,
    val getAllNotesFlow: IGetAllNotesFlow,
    val getNoteById: IGetNoteById,
    val getNoteLinks: IGetNoteLinks,
    val getNoteBacklinks: IGetNoteBacklinks,
    // Insert
    val createNote: ICreateNote,
    // Update
    val archiveNote: IArchiveNote,
    val saveNote: ISaveNote,
    // Delete
    val deleteNote: IDeleteNote,
    val deleteNotes: IDeleteNotes,
    // Filter
    val filterNotes: IFilterNotes,
) {
    companion object Factory {
        fun build(
            noteDataSource: INoteDataSource,
            linkDataSource: ILinkDataSource,
            markdownProcessor: IMarkdownProcessor
        ): NoteInteractors =
            NoteInteractors(
                getAllNotes = GetAllNotes(noteDataSource),
                getAllNotesFlow = GetAllNotesFlow(noteDataSource),
                getNoteById = GetNoteById(noteDataSource),
                getNoteLinks = GetNoteLinks(linkDataSource),
                getNoteBacklinks = GetNoteBacklinks(linkDataSource),
                createNote = CreateNote(noteDataSource),
                archiveNote = ArchiveNote(noteDataSource),
                saveNote = SaveNote(noteDataSource, markdownProcessor),
                deleteNote = DeleteNote(noteDataSource),
                deleteNotes = DeleteNotes(noteDataSource),
                filterNotes = FilterNotes(),
            )
    }
}