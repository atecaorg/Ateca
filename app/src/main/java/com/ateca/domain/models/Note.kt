package com.ateca.domain.models

import java.util.*

data class Note(
    val id: NoteId = NoteId(UUID.randomUUID().toString()),
    val title: String = "",
    val text: String = "",
    val folder: String? = null,
    val archived: Boolean = false,
    val created: Date = Date(System.currentTimeMillis()),
    val modified: Date = Date(System.currentTimeMillis()),
    val links: List<Link> = emptyList(),
    val tags: List<String> = emptyList()
)