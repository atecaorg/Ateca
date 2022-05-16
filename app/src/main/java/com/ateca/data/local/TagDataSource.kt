package com.ateca.data.local

import com.ateca.data.local.room.dao.TagDao
import com.ateca.data.local.room.model.RoomTag
import com.ateca.data.local.room.toModel
import com.ateca.domain.datasource.ITagDataSource
import com.ateca.domain.models.NoteId

/**
 * Created by dronpascal on 16.05.2022.
 */
class TagDataSource(
    private val tagDao: TagDao
) : ITagDataSource {

    override fun getUniqueTags(): List<String> {
        return tagDao.selectDistinct().toModel()
    }

    override fun getTagsById(id: NoteId): List<String> {
        return tagDao.select(id).toModel()
    }

    override fun addTag(id: NoteId, tag: String) {
        tagDao.insert(RoomTag(id, tag))
    }

    override fun addTags(id: NoteId, tags: List<String>) {
        tags.forEach { addTag(id, it) }
    }
}