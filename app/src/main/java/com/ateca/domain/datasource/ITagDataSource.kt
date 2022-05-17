package com.ateca.domain.datasource

import com.ateca.domain.models.NoteId

interface ITagDataSource {

    suspend fun getUniqueTags(): List<String>

    suspend fun getTagsById(id: NoteId): List<String>

    suspend fun addTag(id: NoteId, tag: String)

    suspend fun addTags(id: NoteId, tags: List<String>)
}