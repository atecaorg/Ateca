package com.ateca.domain.interactors

import com.ateca.domain.core.AppDispatchers
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
            markdownProcessor: IMarkdownProcessor,
            dispatchers: AppDispatchers,
        ): NoteInteractors =
            NoteInteractors(
                getAllNotes = GetAllNotes(noteDataSource, dispatchers),
                getAllNotesFlow = GetAllNotesFlow(noteDataSource, dispatchers),
                getNoteById = GetNoteById(noteDataSource, dispatchers),
                getNoteLinks = GetNoteLinks(linkDataSource, dispatchers),
                getNoteBacklinks = GetNoteBacklinks(linkDataSource, dispatchers),
                createNote = CreateNote(noteDataSource, dispatchers),
                archiveNote = ArchiveNote(noteDataSource, dispatchers),
                saveNote = SaveNote(noteDataSource, markdownProcessor, dispatchers),
                deleteNote = DeleteNote(noteDataSource, dispatchers),
                deleteNotes = DeleteNotes(noteDataSource, dispatchers),
                filterNotes = FilterNotes(dispatchers),
            )
    }
}