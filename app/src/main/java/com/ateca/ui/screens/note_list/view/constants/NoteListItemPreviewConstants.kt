package com.ateca.ui.screens.note_list.view.constants

import com.ateca.domain.models.Note
import com.ateca.ui.components.AppPreviewConstants

/**
 * Created by dronpascal on 20.05.2022.
 */
object NoteListItemPreviewConstants {

    val item by lazy {
        Note(
            title = AppPreviewConstants.title,
            text = AppPreviewConstants.text,
        )
    }
}