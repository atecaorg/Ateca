package com.ateca.data.local.database.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel
import com.ateca.domain.model.NotesStatusModel

@Entity(tableName = "notes_status", primaryKeys = ["noteStatusUID"])
data class NoteStatusPojo(
    @ColumnInfo(name = "noteStatusUID")
    val noteStatusUID: String,
    @ColumnInfo(name = "statusName")
    val statusName: String
) : IEntityModel {
    override fun convertToModel(): IModel =
        NotesStatusModel(
            noteStatusUID = noteStatusUID,
            statusName = statusName
        )
}
