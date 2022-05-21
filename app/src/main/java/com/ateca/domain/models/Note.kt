package com.ateca.domain.models

import java.util.*

data class Note(
    val id: NoteId = UUID.randomUUID().toString(),
    val title: String = "",
    val text: String = "",
    val folder: String? = null,
    val archived: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val modifiedAt: Long = System.currentTimeMillis(),
    val links: List<Link> = emptyList(),
    val tags: List<String> = emptyList()
)