package com.ateca.domain.interactors.note

import com.ateca.R
import com.ateca.domain.core.*
import com.ateca.domain.interactors.NoteInteractor
import com.ateca.domain.interactors.debugBehavior
import com.ateca.domain.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

/**
 * Created by dronpascal on 29.05.2022.
 *
 * Does not access any DataSources.
 * This helps to keep the filtering logic isolated.
 */
class FilterNotes : NoteInteractor.IFilterNotes {

    override fun execute(
        notesToFilter: List<Note>,
        textFilter: String,
        sortType: SortType,
        sortOrder: SortOrder,
    ): Flow<DataState<List<Note>>> = flow {

        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))

            val filteredList: MutableList<Note> = notesToFilter.filter {
                val fullText = it.text + " " + it.title
                fullText.lowercase().contains(textFilter.lowercase())
            }.toMutableList()

            when (sortType) {
                is SortType.Idle -> {}
                is SortType.Created -> {
                    val sortLambda = { note: Note -> note.createdAt }
                    filteredList.applySortOrder(sortOrder, sortLambda)
                }
                is SortType.Modified -> {
                    val sortLambda = { note: Note -> note.modifiedAt }
                    filteredList.applySortOrder(sortOrder, sortLambda)
                }
                is SortType.Name -> {
                    val sortLambda = { note: Note -> note.title }
                    filteredList.applySortOrder(sortOrder, sortLambda)
                }
            }

            emit(DataState.Data(Collections.unmodifiableList(filteredList)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = UIText.StringResource(R.string.error),
                        description = e.message
                            ?.let { UIText.DynamicString(it) }
                            ?: UIText.StringResource(R.string.filter_note_error_msg)
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }.flowOn(Dispatchers.Default)


    private fun <T, R : Comparable<R>> MutableList<T>.applySortOrder(
        sortOrder: SortOrder,
        sortLambda: (T) -> R?
    ) {
        when (sortOrder) {
            is SortOrder.Idle -> {}
            is SortOrder.Descending -> {
                this.sortByDescending(sortLambda)
            }
            is SortOrder.Ascending -> {
                this.sortBy(sortLambda)
            }
        }
    }
}


