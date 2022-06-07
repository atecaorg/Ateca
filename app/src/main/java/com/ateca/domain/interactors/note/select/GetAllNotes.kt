package com.ateca.domain.interactors.note.select

import com.ateca.R
import com.ateca.domain.core.AppDispatchers
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.IGetAllNotes
import com.ateca.domain.interactors.util.debugBehavior
import com.ateca.domain.interactors.util.genericDialogResponse
import com.ateca.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 16.05.2022.
 */
class GetAllNotes(
    private val noteSource: INoteDataSource,
    private val dispatchers: AppDispatchers,
) : IGetAllNotes {

    override fun execute(param: Unit): Flow<DataState<List<Note>>> = flow {
        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val notes: List<Note> = noteSource.selectAll()
                .sortedByDescending { it.createdAt }
            emit(DataState.Data(notes))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(genericDialogResponse(e, R.string.error, R.string.get_all_notes_error_msg))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }.flowOn(dispatchers.io)
}