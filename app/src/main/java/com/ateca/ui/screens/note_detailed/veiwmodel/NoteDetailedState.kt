package com.ateca.ui.screens.note_detailed.veiwmodel

import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.Queue
import com.ateca.domain.core.UIComponent
import com.ateca.domain.models.Note

/**
 * Created by dronpascal on 09.06.2022.
 */
data class NoteDetailedState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),

    val note: Note = Note(),
    val mode: NoteUIMode = NoteUIMode.EditMode
)