package com.ateca.data.local.database.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel
import com.ateca.domain.model.NoteRefModel

@Entity(tableName = "notes_refs", primaryKeys = ["noteRefUID"])
data class NoteRefPojo(
    @ColumnInfo(name = "noteRefUID")
    val noteRefUID: String,
    @ColumnInfo(name = "noteUID")
    val noteUID: String,
    @ColumnInfo(name = "refNoteUID")
    val refNoteUID: String
) : IEntityModel {
    override fun convertToModel(): IModel =
        NoteRefModel(
            noteRefUID = noteRefUID,
            noteUID = noteUID,
            refNoteUID = refNoteUID
        )
}