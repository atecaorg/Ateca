package com.ateca.ui.screens.note_list.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ateca.domain.core.*
import com.ateca.domain.interactors.ICreateNote
import com.ateca.domain.interactors.IFilterNotes
import com.ateca.domain.interactors.NoteInteractors
import com.ateca.domain.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dronpascal on 21.05.2022.
 */
@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteInteractors: NoteInteractors,
) : ViewModel() {

    val state: MutableState<NoteListState> = mutableStateOf(NoteListState())

    private val currentQuery = MutableStateFlow("")
    private var currentSearchJob: Job? = null

    init {
        onTriggerEvent(NoteListEvents.GetAllNotes)
        initSearchFlow()
    }

    @OptIn(FlowPreview::class)
    private fun initSearchFlow() {
        currentQuery
            .debounce(300)
            .distinctUntilChanged()
            .onEach { query ->
                // Interrupt previous search job before new one.
                currentSearchJob?.cancel()
                getFilteredNotes(query)
            }
            .launchIn(viewModelScope)
    }

    fun onTriggerEvent(event: NoteListEvents) {
        when (event) {
            is NoteListEvents.GetAllNotes -> getAllNoteItems()
            is NoteListEvents.OnRemoveHeadFromQueue -> removeHeadMessage()
            is NoteListEvents.OnAddTestNoteClicked -> onAddTestNote()
            is NoteListEvents.OnNoteLongPress -> onNoteLongPress(event.note)
            is NoteListEvents.SelectAll -> onSelectAll()
            is NoteListEvents.UnselectAll -> onUnselectAll()
            is NoteListEvents.DeleteSelected -> onDeleteSelected()
            is NoteListEvents.OnQueryChanged -> onQueryChanged(event.query)
        }
    }

    private fun getAllNoteItems() {
        noteInteractors.getAllNotes.execute(Unit).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> dataState.handle()
                is DataState.Data -> {
                    state.value = state.value.copy(noteItems = dataState.data ?: listOf())
                    state.value = state.value.copy(filteredNoteItems = state.value.noteItems)
                }
                is DataState.Loading -> dataState applyTo state
            }
        }.launchIn(viewModelScope)
    }

    private fun onQueryChanged(newQuery: String) {
        currentQuery.value = newQuery
    }

    /**
     * TODO Optimization - Handle case when list is only shrinking.
     *  In this case we have to use notesToFilter = state.value.filteredNoteItems
     *  Otherwise notesToFilter = state.value.noteItems
     */
    private fun getFilteredNotes(query: String) {
        currentSearchJob = noteInteractors.filterNotes.execute(
            IFilterNotes.Parameter(
                notesToFilter = state.value.noteItems,
                textFilter = query,
                sortType = SortType.Modified,
                sortOrder = SortOrder.Descending
            )
        ).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> dataState.handle()
                is DataState.Data -> {
                    state.value = state.value.copy(
                        filteredNoteItems = dataState.data ?: emptyList()
                    )
                }
                is DataState.Loading -> dataState applyTo state
            }
        }.launchIn(viewModelScope)
    }

    var isAddProcessing = false
    private fun onAddTestNote() {
        if (isAddProcessing) return
        noteInteractors.createNote.execute(ICreateNote.Parameter())
            .onStart { isAddProcessing = true }
            .onEach { dataState ->
                when (dataState) {
                    is DataState.Response -> dataState.handle()
                    is DataState.Data -> {
                        val newList = state.value.noteItems.toMutableList()
                        dataState.data?.let { newList.add(0, it) }
                        state.value = state.value.copy(noteItems = newList)
                    }
                    is DataState.Loading -> dataState applyTo state
                }
            }.launchIn(viewModelScope)
            .invokeOnCompletion { isAddProcessing = false }
    }

    private fun onNoteLongPress(note: Note) {
        val oldState = state.value
        val noteId = note.id
        val newState = if (oldState.selectedIds.isEmpty()) {
            val selectedIds = listOf(noteId)
            oldState.copy(selectedIds = selectedIds)
        } else {
            val oldSelectedIds = oldState.selectedIds.toMutableList()
            val alreadySelected = oldSelectedIds.contains(noteId)
            val selectedIds = if (alreadySelected) {
                oldSelectedIds - noteId
            } else {
                oldSelectedIds + noteId
            }
            oldState.copy(selectedIds = selectedIds)
        }
        state.value = newState
    }

    private fun onSelectAll() {
        val selectedIds = state.value.noteItems.map { it.id }
        state.value = state.value.copy(selectedIds = selectedIds)
    }

    private fun onUnselectAll() {
        state.value = state.value.copy(selectedIds = emptyList())
    }

    private fun onDeleteSelected() {
        viewModelScope.launch {
            val selectedIds = state.value.selectedIds
            state.value = state.value.copy(selectedIds = emptyList())
            noteInteractors.deleteNotes.execute(selectedIds).onEach { dataState ->
                when (dataState) {
                    is DataState.Response -> dataState.handle()
                    is DataState.Data -> onTriggerEvent(NoteListEvents.GetAllNotes)
                    is DataState.Loading -> dataState applyTo state
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        val queue = state.value.errorQueue
        queue.add(uiComponent)
        // TODO delete line below if recompose bug fixed
        state.value = state.value.copy(errorQueue = Queue(mutableListOf()))
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove()
            // TODO delete line below if recompose bug fixed
            state.value = state.value.copy(errorQueue = Queue(mutableListOf()))
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            Log.i("NoteListViewModel", "Nothing to remove from MessageQueue")
        }
    }

    private fun <T> DataState.Response<T>.handle() {
        when (val uiComponent = this.uiComponent) {
            is UIComponent.Dialog -> appendToMessageQueue(uiComponent)
            else -> {}
        }
    }

    private infix fun <T> DataState.Loading<T>.applyTo(state: MutableState<NoteListState>) {
        state.value = state.value.copy(progressBarState = this.progressBarState)
    }
}

