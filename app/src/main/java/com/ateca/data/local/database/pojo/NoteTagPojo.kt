package com.ateca.data.local.database.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel

@Entity(tableName = "notes_tags", primaryKeys = ["noteTagUID"])
data class NoteTagPojo(
    @ColumnInfo(name = "noteTagUID")
    val noteTagUID: String,
    @ColumnInfo(name = "noteUID")
    val noteUID: String,
    @ColumnInfo(name = "tagUID")
    val tagUID: String
) : IEntityModel {
    override fun convertToModel(): IModel {
        TODO("Not yet implemented")
    }
}
