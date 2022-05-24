package com.ateca.domain.interactors.note.insert

import androidx.core.text.isDigitsOnly
import com.ateca.R
import com.ateca.domain.constants.NoteConstants.BASE_TITLE
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.core.UIText
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.interactors.NoteInteractor
import com.ateca.domain.interactors.debugBehavior
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Created by dronpascal on 17.05.2022.
 */
class CreateNote(
    private val noteSource: INoteDataSource,
) : NoteInteractor.ICreateNote {

    override fun execute(id: NoteId?, title: String?): Flow<DataState<Note>> = flow {
        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            val noteId = id ?: UUID.randomUUID().toString()
            val noteTitle = title
                ?: withContext(Dispatchers.Default) {
                    getLessUniqueTitle()
                }
            val newNote = Note(
                id = noteId,
                title = noteTitle
            )
            noteSource.saveNote(newNote)

            emit(DataState.Data(newNote))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = UIText.StringResource(R.string.error),
                        description = e.message
                            ?.let { UIText.DynamicString(it) }
                            ?: UIText.StringResource(R.string.create_note_error_msg)
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

    /**
     * Search less base unique title for new note.
     *
     * Example:
     *
     * For [Untitled 1, Untitled 2] less is [Untitled 0].
     *
     * For [Untitled 0, Untitled 2] less is [Untitled 1].
     *
     * TODO: Try to use kotlin sequence
     */
    private suspend fun getLessUniqueTitle(): String {
        try {
            val baseTitles = noteSource.selectBaseTitles()
            val secondParts = baseTitles.map { it.split(" ").last() }
            val onlyDigits = secondParts.filter { it.isDigitsOnly() }
            val onlyNumbers = listOf(-1) + onlyDigits.map { it.toInt() }.sorted().distinct()

            if (onlyNumbers.isEmpty()) return "$BASE_TITLE 0"
            for (i in 1 until onlyNumbers.size) {
                val expectedNum = onlyNumbers[i - 1] + 1
                if (onlyNumbers[i] != expectedNum)
                    return "$BASE_TITLE $expectedNum"
            }
            return "$BASE_TITLE ${onlyNumbers.last() + 1}"
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            throw Exception("Failed to find unique title")
        }
    }
}