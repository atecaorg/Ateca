package com.ateca.ui.screens.note_list.view.constants

import com.ateca.domain.models.Note
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.screens.note_list.viewmodel.NoteListState

/**
 * Created by dronpascal on 20.05.2022.
 */
object NoteListPreviewConstants {

    val items by lazy {
        List(10) { index ->
            Note(
                id = index.toString(),
                title = AppPreviewConstants.title,
                text = AppPreviewConstants.text,
            )
        }
    }

    val selectedIds by lazy {
        listOf("2", "4", "5")
    }

    val state by lazy {
        NoteListState(
            noteItems = items,
            selectedIds = selectedIds
        )
    }
}