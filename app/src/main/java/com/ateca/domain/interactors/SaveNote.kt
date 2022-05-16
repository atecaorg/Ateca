package com.ateca.domain.interactors

import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.datasource.ITagDataSource
import com.ateca.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 17.05.2022.
 */
class SaveNote(
    private val noteSource: INoteDataSource,
    private val linkSource: ILinkDataSource,
    private val tagSource: ITagDataSource
) {
    fun execute(note: Note): Flow<DataState<Nothing>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            noteSource.deleteNoteById(note.id)
            noteSource.saveNote(note)
            linkSource.addLinks(note.links)
            tagSource.addTags(note.id, note.tags)
        } catch (e: Exception) {
            noteSource.deleteNoteById(note.id)
            e.printStackTrace()
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without Response type
                DataState.Response<Nothing>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Failed to save note"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}