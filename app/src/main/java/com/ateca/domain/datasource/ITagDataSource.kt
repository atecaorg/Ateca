package com.ateca.domain.datasource

import com.ateca.domain.models.NoteId

interface ITagDataSource {

    fun getUniqueTags(): List<String>

    fun getTagsById(id: NoteId): List<String>

    fun addTag(id: NoteId, tag: String)

    fun addTags(id: NoteId, tags: List<String>)
}