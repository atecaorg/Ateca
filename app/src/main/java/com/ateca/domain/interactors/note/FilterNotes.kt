package com.ateca.domain.interactors.note

import com.ateca.R
import com.ateca.domain.core.*
import com.ateca.domain.interactors.IFilterNotes
import com.ateca.domain.interactors.util.debugBehavior
import com.ateca.domain.interactors.util.genericDialogResponse
import com.ateca.domain.models.Note
import kotlinx.coroutines.CancellationException
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
class FilterNotes(
    private val dispatchers: IAppDispatchers,
) : IFilterNotes {

    override fun execute(
        param: IFilterNotes.Parameter
    ): Flow<DataState<List<Note>>> = flow {
        val notesToFilter = param.notesToFilter
        val textFilter = param.textFilter
        val sortType = param.sortType
        val sortOrder = param.sortOrder

        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            if (textFilter.isBlank()) {
                emit(DataState.Data(notesToFilter))
                throw CancellationException("Nothing to filter.")
            }

            val filteredList: MutableList<Note> = notesToFilter.filter {
                val fullText = it.text + " " + it.title
                fullText.lowercase().contains(textFilter.lowercase())
            }.toMutableList()
            applySort(filteredList, sortType, sortOrder)

            emit(DataState.Data(Collections.unmodifiableList(filteredList)))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emit(genericDialogResponse(e, R.string.error, R.string.filter_note_error_msg))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }.flowOn(dispatchers.default)

    private fun applySort(
        filteredList: MutableList<Note>,
        sortType: SortType,
        sortOrder: SortOrder
    ) {
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
    }

    private inline fun <T, R : Comparable<R>> MutableList<T>.applySortOrder(
        sortOrder: SortOrder,
        crossinline sortLambda: (T) -> R?
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


