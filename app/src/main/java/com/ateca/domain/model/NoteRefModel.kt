package com.ateca.domain.model

import com.ateca.data.local.database.pojo.NoteRefPojo
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel

data class NoteRefModel(
    val noteRefUID: String,
    val noteUID: String,
    val refNoteUID: String
) : IModel {
    override fun convertToPojo(): IEntityModel =
        NoteRefPojo(
            noteRefUID = noteRefUID,
            noteUID = noteUID,
            refNoteUID = refNoteUID
        )
}
