package com.ateca.domain.model

import com.ateca.data.local.database.pojo.NoteTagPojo
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel

data class NoteTagModel(
    val noteTagUID: String,
    val noteUID: String,
    val tagUID: String
) : IModel {
    override fun convertToPojo(): IEntityModel =
        NoteTagPojo(
            noteTagUID = noteTagUID,
            noteUID = noteUID,
            tagUID = tagUID
        )
}
