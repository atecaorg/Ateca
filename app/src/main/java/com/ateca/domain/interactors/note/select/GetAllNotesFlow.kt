package com.ateca.domain.interactors.note.select

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.IAppDispatchers
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.IGetAllNotesFlow
import com.ateca.domain.interactors.util.debugBehavior
import com.ateca.domain.interactors.util.genericDialogResponse
import com.ateca.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Created by dronpascal on 31.05.2022.
 */
class GetAllNotesFlow(
    private val noteSource: INoteDataSource,
    private val dispatchers: IAppDispatchers,
) : IGetAllNotesFlow {

    override fun execute(param: Unit): Flow<DataState<Flow<List<Note>>>> = flow {
        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val notesFlow = noteSource.getAllDistinctUntilChanged().map { list ->
                list.sortedByDescending { it.createdAt }
            }
            emit(DataState.Data(notesFlow))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(genericDialogResponse(e, R.string.error, R.string.get_all_notes_error_msg))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }.flowOn(dispatchers.io)
}