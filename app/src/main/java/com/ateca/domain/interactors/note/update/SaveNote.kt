package com.ateca.domain.interactors.note.update

import com.ateca.R
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.entity.IMarkdownProcessor
import com.ateca.domain.interactors.ISaveNote
import com.ateca.domain.interactors.util.debugBehavior
import com.ateca.domain.interactors.util.genericDialogResponse
import com.ateca.domain.models.Link
import com.ateca.domain.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 17.05.2022.
 */
class SaveNote(
    private val noteSource: INoteDataSource,
    private val markdownParser: IMarkdownProcessor
) : ISaveNote {

    override fun execute(note: Note): Flow<DataState<Nothing>> = flow {
        try {
            debugBehavior()
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
                    modifiedAt = System.currentTimeMillis(),
                    links = links,
                    tags = tags
                )
            )
            emit(DataState.Data())
        } catch (e: Exception) {
            e.printStackTrace()
            emit(genericDialogResponse(e, R.string.error, R.string.save_note_error_msg))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}