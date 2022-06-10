package com.ateca.domain.interactors.note.update

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.IAppDispatchers
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.IArchiveNote
import com.ateca.domain.interactors.util.debugBehavior
import com.ateca.domain.interactors.util.genericDialogResponse
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 16.05.2022.
 */
class ArchiveNote(
    private val noteSource: INoteDataSource,
    private val dispatchers: IAppDispatchers,
) : IArchiveNote {

    override fun execute(param: NoteId): Flow<DataState<Nothing>> = flow<DataState<Nothing>> {
        @Suppress("UnnecessaryVariable")
        val id = param

        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            noteSource.setArchived(id, true)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(genericDialogResponse(e, R.string.error, R.string.archive_note_error_msg))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }.flowOn(dispatchers.io)
}