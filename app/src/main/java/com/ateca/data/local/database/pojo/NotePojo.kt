package com.ateca.data.local.database.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel
import com.ateca.domain.model.NoteModel

@Entity(tableName = "notes", primaryKeys = ["noteUID"])
data class NotePojo(
    @ColumnInfo(name = "noteUID")
    val noteUID: String,
    @ColumnInfo(name = "noteTitle")
    val noteTitle: String,
    @ColumnInfo(name = "noteText")
    val noteText: String,
    @ColumnInfo(name = "noteStatusUID")
    val noteStatusUID: String
) : IEntityModel {
    override fun convertToModel(): IModel =
        NoteModel(
            noteUID = noteUID,
            noteTitle = noteTitle,
            noteText = noteText,
            noteStatusUID = noteStatusUID
        )
}
