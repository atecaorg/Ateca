package com.ateca.domain.interactors.note.delete

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.core.UIText
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.IDeleteNote
import com.ateca.domain.interactors.debugBehavior
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 16.05.2022.
 */
class DeleteNote(
    private val noteSource: INoteDataSource,
) : IDeleteNote {

    override fun execute(id: NoteId): Flow<DataState<Nothing>> = flow {
        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            noteSource.deleteNoteById(id)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = UIText.StringResource(R.string.error),
                        description = e.message
                            ?.let { UIText.DynamicString(it) }
                            ?: UIText.StringResource(R.string.delete_note_error_msg)
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}