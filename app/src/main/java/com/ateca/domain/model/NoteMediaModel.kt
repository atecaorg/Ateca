package com.ateca.domain.model

import com.ateca.data.local.database.pojo.NoteMediaPojo
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel

data class NoteMediaModel(
    val noteMediaUID: String,
    val noteUID: String,
    val noteMediaPath: String
) : IModel {
    override fun convertToPojo(): IEntityModel =
        NoteMediaPojo(
            noteMediaUID = noteMediaUID,
            noteUID = noteUID,
            noteMediaPath = noteMediaPath
        )
}
