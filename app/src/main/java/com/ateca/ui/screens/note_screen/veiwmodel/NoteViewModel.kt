package com.ateca.ui.screens.note_screen.veiwmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ateca.domain.constants.NavigationConstants.NOTE_ID_ARGUMENT_KEY
import com.ateca.domain.core.DataState
import com.ateca.domain.interactors.NoteInteractors
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteInteractors: NoteInteractors,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val ioDispatcher = Dispatchers.IO
    private val scope = CoroutineScope(ioDispatcher)

    private val noteId: NoteId =
        savedStateHandle.get<String>(NOTE_ID_ARGUMENT_KEY) ?: UUID.randomUUID().toString()

    private var _note: MutableStateFlow<Note> = MutableStateFlow(Note())
    val note: StateFlow<Note> = _note

    private var _saveState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val saveState: StateFlow<Boolean> = _saveState

    private val noteDataState: MutableStateFlow<DataState<Note>> =
        MutableStateFlow(DataState.Loading())

    init {
        viewModelScope.launch(ioDispatcher) {
            noteInteractors.getNoteById.execute(noteId).collect {
                noteDataState.value = it
                Log.d(TAG, it.toString())
                if (it is DataState.Data) {
                    _note.value = it.data ?: getNoteMockUseCase()
                }
            }
        }
    }

    fun updateNote(
        title: String = note.value.title,
        text: String = note.value.text,
        folder: String? = note.value.folder,
        tags: List<String> = note.value.tags
    ) {
        _note.value = Note(
            id = note.value.id,
            title = title,
            text = text,
            folder = folder,
            tags = tags
        )
    }

    fun saveNote() {
        viewModelScope.launch(ioDispatcher) {
            noteInteractors.saveNote.execute(note.value).collect { it ->
                if (it is DataState.Data) {
                    _saveState.value = true
                }
            }
        }
    }

    private fun getNoteMockUseCase(): Note =
        Note(
            title = "SpaceXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            text = "Space Exploration Technologies Corp. is an American space manufacturer, a provider of space transportation services, and a communications corporation headquartered in Hawthorne, California. SpaceX was founded in 2002 by Elon Musk with the goal of reducing space transportation costs to enable the colonization of Mars.",
            folder = "spaceX",
            tags = listOf("spaceX", "Elon Mask", "space", "космос", "mars")
        )

    companion object {
        private const val TAG = "NOTE_VIEW_MODEL"
    }
}