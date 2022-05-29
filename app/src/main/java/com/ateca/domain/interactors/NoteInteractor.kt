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
sealed interface NoteInteractor {

    interface IDeleteNote : NoteInteractor {
        fun execute(id: NoteId): Flow<DataState<Nothing>>
    }

    interface IDeleteNotes : NoteInteractor {
        fun execute(ids: List<NoteId>): Flow<DataState<Nothing>>
    }

    interface ICreateNote : NoteInteractor {
        fun execute(id: NoteId? = null, title: String? = null): Flow<DataState<Note>>
    }

    interface IGetAllNotes : NoteInteractor {
        fun execute(): Flow<DataState<List<Note>>>
    }

    interface IGetNoteById : NoteInteractor {
        fun execute(id: NoteId): Flow<DataState<Note>>
    }

    interface IGetNoteBacklinks : NoteInteractor {
        fun execute(id: NoteId): Flow<DataState<List<Link>>>
    }

    interface IGetNoteLinks : NoteInteractor {
        fun execute(id: NoteId): Flow<DataState<List<Link>>>
    }

    interface IArchiveNote : NoteInteractor {
        fun execute(id: NoteId): Flow<DataState<Nothing>>
    }

    interface ISaveNote : NoteInteractor {
        fun execute(note: Note): Flow<DataState<Nothing>>
    }

    interface IFilterNotes : NoteInteractor {
        fun execute(
            notesToFilter: List<Note>,
            textFilter: String,
            sortType: SortType,
            sortOrder: SortOrder
        ): Flow<DataState<List<Note>>>
    }
}