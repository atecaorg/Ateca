package com.ateca.ui.screens.note_list.viewmodel

import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.Queue
import com.ateca.domain.core.UIComponent
import com.ateca.domain.models.Note
import com.ateca.domain.models.NoteId

/**
 * Created by dronpascal on 20.05.2022.
 */
data class NoteListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val noteItems: List<Note> = emptyList(),
    val selectedIds: List<NoteId> = emptyList(),
    val selectedNote: Note? = null, // selected only one => != null
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)

