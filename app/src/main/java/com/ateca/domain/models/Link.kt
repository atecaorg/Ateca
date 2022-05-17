package com.ateca.domain.models

/**
 * Created by dronpascal on 16.05.2022.
 */
data class Link(
    val name: String,
    val primaryNoteId: NoteId,
    val linkedNoteId: NoteId
)
