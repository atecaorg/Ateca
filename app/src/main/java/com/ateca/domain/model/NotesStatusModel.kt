package com.ateca.domain.model

import com.ateca.data.local.database.pojo.NoteStatusPojo
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel

data class NotesStatusModel(
    val noteStatusUID: String,
    val statusName: String
) : IModel {
    override fun convertToPojo(): IEntityModel =
        NoteStatusPojo(
            noteStatusUID = noteStatusUID,
            statusName = statusName
        )
}