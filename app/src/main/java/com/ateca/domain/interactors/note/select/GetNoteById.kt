package com.ateca.domain.interactors.note.select

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.core.UIText
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.NoteInteractor
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 17.05.2022.
 */
class GetNoteById(
    private val noteSource: INoteDataSource,
) : NoteInteractor.IGetNoteById {

    override fun execute(id: NoteId): Flow<DataState<Note>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val note: Note = noteSource.select(id)
            emit(DataState.Data(note))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without Response type
                DataState.Response<Note>(
                    uiComponent = UIComponent.Dialog(
                        title = UIText.StringResource(R.string.error),
                        description = e.message
                            ?.let { UIText.DynamicString(it) }
                            ?: UIText.StringResource(R.string.get_note_error_msg)
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}