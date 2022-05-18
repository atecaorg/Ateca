package com.ateca.domain.interactors.note.update

import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.entity.IMarkdownProcessor
import com.ateca.domain.models.Link
import com.ateca.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

/**
 * Created by dronpascal on 17.05.2022.
 */
class SaveNote(
    private val noteSource: INoteDataSource,
    private val markdownParser: IMarkdownProcessor
) {
    fun execute(note: Note): Flow<DataState<Nothing>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val tags = markdownParser.getTagSubstrings(note.text)
            val links = markdownParser.getCrossLinkSubstrings(note.text)
                .map { title ->
                    Link(
                        name = title,
                        primaryNoteId = note.id,
                        linkedNoteId = noteSource.getIdByTitle(title)
                    )
                }
            noteSource.saveNote(
                note.copy(
                    modifiedAt = Date(System.currentTimeMillis()),
                    links = links,
                    tags = tags
                )
            )
        } catch (e: Exception) {
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