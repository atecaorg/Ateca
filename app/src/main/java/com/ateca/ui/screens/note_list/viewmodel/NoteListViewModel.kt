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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
            is NoteListEvents.GetAllNotes -> {
                getNoteItems()
            }
            is NoteListEvents.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            is NoteListEvents.OnAddTestNoteClicked -> {
                onAddTestWishClicked()
            }
            else -> {
            }
        }
    }

    private fun getNoteItems() {
        noteInteractors.getAllNotes.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (val uiComponent = dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            appendToMessageQueue(uiComponent)
                        }
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

    fun onAddTestWishClicked() {
        noteInteractors.createNote.execute().onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (val uiComponent = dataState.uiComponent) {
                        is UIComponent.Dialog -> {
                            appendToMessageQueue(uiComponent)
                        }
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