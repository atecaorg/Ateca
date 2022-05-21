package com.ateca.domain.interactors.note.select

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.core.UIText
import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.interactors.NoteInteractor
import com.ateca.domain.models.Link
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 18.05.2022.
 */
class GetNoteBacklinks(
    private val linkSource: ILinkDataSource,
) : NoteInteractor.IGetNoteBacklinks {

    override fun execute(id: NoteId): Flow<DataState<List<Link>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val links = linkSource.getBacklinksById(id)
            emit(DataState.Data(links))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without Response type
                DataState.Response<List<Link>>(
                    uiComponent = UIComponent.Dialog(
                        title = UIText.StringResource(R.string.error),
                        description = e.message
                            ?.let { UIText.DynamicString(it) }
                            ?: UIText.StringResource(R.string.get_note_backlinks_error_msg)
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}