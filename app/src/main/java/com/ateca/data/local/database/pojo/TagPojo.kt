package com.ateca.data.local.database.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel
import com.ateca.domain.model.TagModel

@Entity(tableName = "tags", primaryKeys = ["tagUID"])
data class TagPojo(
    @ColumnInfo(name = "tagUID")
    val tagUID: String,
    @ColumnInfo(name = "tagName")
    val tagName: String
) : IEntityModel {
    override fun convertToModel(): IModel =
        TagModel(
            tagUID = tagUID,
            tagName = tagName
        )
}
