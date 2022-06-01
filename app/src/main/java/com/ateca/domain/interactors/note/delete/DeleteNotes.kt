package com.ateca.domain.interactors.note.delete

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.IDeleteNotes
import com.ateca.domain.interactors.util.debugBehavior
import com.ateca.domain.interactors.util.genericDialogResponse
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 24.05.2022.
 */
class DeleteNotes(
    private val noteSource: INoteDataSource,
) : IDeleteNotes {

    @Suppress("UnnecessaryVariable")
    override fun execute(param: List<NoteId>): Flow<DataState<Nothing>> = flow {
        val ids = param

        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            noteSource.deleteNotesByIds(ids)
            // TODO: Find other way to proof the success of work
            // Maybe new sealed class in DataState.Response
            emit(DataState.Data(null))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(genericDialogResponse(e, R.string.error, R.string.delete_note_error_msg))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}