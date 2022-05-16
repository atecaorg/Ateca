package com.ateca.domain.model

import com.ateca.data.local.database.pojo.NotePojo
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel

data class NoteModel(
    val noteUID: String,
    val noteTitle: String,
    val noteText: String,
    val noteStatusUID: String
) : IModel {
    override fun convertToPojo(): IEntityModel =
        NotePojo(
            noteUID = noteUID,
            noteTitle = noteTitle,
            noteText = noteText,
            noteStatusUID = noteStatusUID
        )
}
