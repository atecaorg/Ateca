package com.ateca.domain.interactors.note.select

import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.models.Link
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 18.05.2022.
 */
class GetNoteLinks(
    private val linkSource: ILinkDataSource,
) {
    fun execute(id: NoteId): Flow<DataState<List<Link>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val links = linkSource.getLinksById(id)
            emit(DataState.Data(links))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without Response type
                DataState.Response<List<Link>>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Failed to get note links"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}