package com.ateca.domain.model

import com.ateca.data.local.database.pojo.TagPojo
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel

data class TagModel(
    val tagUID: String,
    val tagName: String
) : IModel {
    override fun convertToPojo(): IEntityModel =
        TagPojo(
            tagUID = tagUID,
            tagName = tagName
        )
}
