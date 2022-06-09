package com.ateca.ui.screens.note_detailed.veiwmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ateca.R
import com.ateca.domain.constants.NavigationConstants.NOTE_ID_ARGUMENT_KEY
import com.ateca.domain.constants.NoteConstants
import com.ateca.domain.core.DataState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.core.UIText
import com.ateca.domain.interactors.NoteInteractors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by eugenics on 20.05.2022.
 * Modified by dronpascal on 09.06.2022.
 */
@HiltViewModel
class NoteDetailedViewModel @Inject constructor(
    private val interactors: NoteInteractors,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state: MutableState<NoteDetailedState> = mutableStateOf(NoteDetailedState())

    init {
        savedStateHandle.get<String>(NOTE_ID_ARGUMENT_KEY)?.let { noteId ->
            if (noteId != NoteConstants.BLANK_NOTE_ID) {
                onTriggerEvent(NoteDetailedEvents.GetNoteById(noteId))
            }
        } ?: appendToMessageQueue(
            UIComponent.Dialog(
                title = UIText.StringResource(R.string.error),
                description = UIText.StringResource(R.string.get_note_id_parameter_error_msg)
            )
        )
    }

    fun onTriggerEvent(event: NoteDetailedEvents) {
        Log.d("NoteDetailedViewModel", "Event ${event.javaClass.simpleName}. Info $event")
        when (event) {
            is NoteDetailedEvents.GetNoteById -> onGetNoteById(event.noteId)
            is NoteDetailedEvents.UpdateTitle -> onUpdateTitle(event.title)
            is NoteDetailedEvents.UpdateText -> onUpdateText(event.text)
            is NoteDetailedEvents.SaveNote -> onSaveNote()
            is NoteDetailedEvents.ChangeUIMode -> onChangeUIMode()
            is NoteDetailedEvents.RemoveHeadFromMessageQueue -> onRemoveHeadMessage()
        }
    }

    private fun onGetNoteById(noteId: String) {
        interactors.getNoteById.execute(noteId).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> dataState.handle()
                is DataState.Data -> dataState.data?.let { note ->
                    state.value = state.value.copy(note = note)
                    Log.d("NoteDetailedViewModel", "$note")
                }
                is DataState.Loading -> dataState applyTo state
            }
        }.launchIn(viewModelScope)
    }

    private fun onUpdateTitle(newTitle: String) {
        val updatedNote = state.value.note.copy(title = newTitle)
        state.value = state.value.copy(note = updatedNote)
    }

    private fun onUpdateText(newText: String) {
        val updatedNote = state.value.note.copy(text = newText)
        state.value = state.value.copy(note = updatedNote)
    }

    // TODO Add title uniqueness check
    private fun onSaveNote() {
        val note = state.value.note
        if (note.title.isEmpty() && note.text.isEmpty()) return
        interactors.saveNote.execute(note).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> dataState.handle()
                is DataState.Data -> {}
                is DataState.Loading -> dataState applyTo state
            }
        }.launchIn(viewModelScope)
    }

    private fun onChangeUIMode() {
        val curMode = state.value.mode
        state.value = state.value.copy(
            mode = when (curMode) {
                NoteUIMode.ViewMode -> NoteUIMode.EditMode
                NoteUIMode.EditMode -> NoteUIMode.ViewMode
            }
        )
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun onRemoveHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove()
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            Log.i("NoteDetailedViewModel", "Nothing to remove from MessageQueue")
        }
    }

    private fun <T> DataState.Response<T>.handle() {
        when (val uiComponent = this.uiComponent) {
            is UIComponent.Dialog -> appendToMessageQueue(uiComponent)
            else -> {}
        }
    }

    private infix fun <T> DataState.Loading<T>.applyTo(state: MutableState<NoteDetailedState>) {
        state.value = state.value.copy(progressBarState = this.progressBarState)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("NoteDetailedViewModel", "VM Cleared!!!!!!!!!!!!")
    }
}