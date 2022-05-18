package com.ateca.ui.screens.note_screen.veiwmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private val NOTE_ID_ARGUMENT_KEY = UUID.randomUUID().toString()

@HiltViewModel
class NoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val noteId:NoteId =
        savedStateHandle.get<String>(NOTE_ID_ARGUMENT_KEY)?:UUID.randomUUID().toString()
    private val ioDispatcher = Dispatchers.IO

    private var _note: MutableStateFlow<Note> = MutableStateFlow(Note())
    val note: StateFlow<Note> = _note

    init {
        viewModelScope.launch(ioDispatcher) {
            _note.value = getNoteMockUseCase(noteId)
        }
    }

    private fun getNoteMockUseCase(noteId: NoteId): Note =
        Note(
            title = "SpaceX",
            text = "Space Exploration Technologies Corp. is an American space manufacturer, a provider of space transportation services, and a communications corporation headquartered in Hawthorne, California. SpaceX was founded in 2002 by Elon Musk with the goal of reducing space transportation costs to enable the colonization of Mars.",
            folder = "spaceX",
            tags = listOf("spaceX", "Elon Mask", "space", "космос", "mars")
        )
}