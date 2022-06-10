package com.ateca.domain.interactors.note.delete

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.IAppDispatchers
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.IDeleteNote
import com.ateca.domain.interactors.util.debugBehavior
import com.ateca.domain.interactors.util.genericDialogResponse
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 16.05.2022.
 */
class DeleteNote(
    private val noteSource: INoteDataSource,
    private val dispatchers: IAppDispatchers,
) : IDeleteNote {

    override fun execute(param: NoteId): Flow<DataState<Nothing>> = flow<DataState<Nothing>> {
        @Suppress("UnnecessaryVariable")
        val id = param

        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            noteSource.deleteNoteById(id)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(genericDialogResponse(e, R.string.error, R.string.delete_note_error_msg))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }.flowOn(dispatchers.io)
}