package com.ateca.domain.interactors

import com.ateca.domain.core.DataState
import com.ateca.domain.core.SortOrder
import com.ateca.domain.core.SortType
import com.ateca.domain.models.Link
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow

/**
 * Created by dronpascal on 21.05.2022.
 */
sealed interface INoteInteractor<T, R> {
    fun execute(param: T): Flow<DataState<R>>
}

interface IDeleteNote : INoteInteractor<NoteId, Nothing> {
    override fun execute(param: NoteId): Flow<DataState<Nothing>>
}

interface IDeleteNotes : INoteInteractor<List<NoteId>, Nothing> {
    override fun execute(param: List<NoteId>): Flow<DataState<Nothing>>
}

interface ICreateNote : INoteInteractor<ICreateNote.Parameter, Note> {
    override fun execute(param: Parameter): Flow<DataState<Note>>

    class Parameter(
        val id: NoteId? = null,
        val title: String? = null
    )
}

interface IGetAllNotes : INoteInteractor<Unit, List<Note>> {
    override fun execute(param: Unit): Flow<DataState<List<Note>>>
}

interface IGetNoteById : INoteInteractor<NoteId, Note> {
    override fun execute(param: NoteId): Flow<DataState<Note>>
}

interface IGetNoteBacklinks : INoteInteractor<NoteId, List<Link>> {
    override fun execute(param: NoteId): Flow<DataState<List<Link>>>
}

interface IGetNoteLinks : INoteInteractor<NoteId, List<Link>> {
    override fun execute(param: NoteId): Flow<DataState<List<Link>>>
}

interface IArchiveNote : INoteInteractor<NoteId, Nothing> {
    override fun execute(param: NoteId): Flow<DataState<Nothing>>
}

interface ISaveNote : INoteInteractor<Note, Nothing> {
    override fun execute(param: Note): Flow<DataState<Nothing>>
}

interface IFilterNotes : INoteInteractor<IFilterNotes.Parameter, List<Note>> {
    override fun execute(
        param: Parameter
    ): Flow<DataState<List<Note>>>

    class Parameter(
        val notesToFilter: List<Note>,
        val textFilter: String,
        val sortType: SortType,
        val sortOrder: SortOrder
    )
}