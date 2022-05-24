package com.ateca.ui.screens.note_list.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ateca.domain.core.DataState
import com.ateca.domain.core.Queue
import com.ateca.domain.core.UIComponent
import com.ateca.domain.interactors.NoteInteractors
import com.ateca.domain.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dronpascal on 21.05.2022.
 */
@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteInteractors: NoteInteractors,
    // for future state revival after process death
    @Suppress("UNUSED_PARAMETER") savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val state: MutableState<NoteListState> = mutableStateOf(NoteListState())

    init {
        onTriggerEvent(NoteListEvents.GetAllNotes)
    }

    fun onTriggerEvent(event: NoteListEvents) {
        when (event) {
            is NoteListEvents.GetAllNotes -> getNoteItems()
            is NoteListEvents.OnRemoveHeadFromQueue -> removeHeadMessage()
            is NoteListEvents.OnAddTestNoteClicked -> onAddTestNote()
            is NoteListEvents.OnNoteLongPress -> onNoteLongPress(event.note)
            is NoteListEvents.SelectAll -> onSelectAll()
            is NoteListEvents.UnselectAll -> onUnselectAll()
            is NoteListEvents.DeleteSelected -> onDeleteSelected()
        }
    }

    private fun getNoteItems() {
        noteInteractors.getAllNotes.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (val uiComponent = dataState.uiComponent) {
                        is UIComponent.Dialog -> appendToMessageQueue(uiComponent)
                        else -> {}
                    }
                }
                is DataState.Data -> {
                    state.value = state.value.copy(noteItems = dataState.data ?: listOf())
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
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

    var isAddProcessing = false
    private fun onAddTestNote() {
        if (isAddProcessing) return
        isAddProcessing = true
        noteInteractors.createNote.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (val uiComponent = dataState.uiComponent) {
                        is UIComponent.Dialog -> appendToMessageQueue(uiComponent)
                        else -> {}
                    }
                }
                is DataState.Data -> {
                    val newList = state.value.noteItems.toMutableList()
                    dataState.data?.let { newList.add(it) }
                    state.value = state.value.copy(
                        noteItems = newList
                    )
                }
                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
            .invokeOnCompletion { isAddProcessing = false }
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
                    is DataState.Response -> {
                        when (val uiComponent = dataState.uiComponent) {
                            is UIComponent.Dialog -> appendToMessageQueue(uiComponent)
                            else -> {}
                        }
                    }
                    is DataState.Data -> {
                        onTriggerEvent(NoteListEvents.GetAllNotes)
                    }
                    is DataState.Loading -> state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
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
}