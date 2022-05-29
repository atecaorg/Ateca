package com.ateca.domain.interactors.note.select

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.core.UIText
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.IGetAllNotes
import com.ateca.domain.interactors.debugBehavior
import com.ateca.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 16.05.2022.
 */
class GetAllNotes(
    private val noteSource: INoteDataSource,
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
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = UIText.StringResource(R.string.error),
                        description = e.message
                            ?.let { UIText.DynamicString(it) }
                            ?: UIText.StringResource(R.string.get_all_notes_error_msg)
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}