package com.ateca.data.local.room

import com.ateca.data.local.room.model.RoomLink
import com.ateca.data.local.room.model.RoomNote
import com.ateca.domain.models.Link
import com.ateca.domain.models.Note

/**
 * Created by dronpascal on 16.05.2022.
 */
internal fun Note.toEntity() = RoomNote(
    id = this.id,
    title = this.title,
    text = this.text,
    folder = this.folder,
    archived = this.archived,
    createdAt = this.createdAt.time,
    modifiedAt = this.modifiedAt.time
)

internal fun Link.toEntity() = RoomLink(
    name = this.name,
    primaryNoteId = this.primaryNoteId,
    linkedNoteId = this.linkedNoteId,
)

@JvmName("toEntityNoteList")
internal fun List<Note>.toEntity(): List<RoomNote> = this.map { it.toEntity() }

@JvmName("toEntityLinkList")
internal fun List<Link>.toEntity(): List<RoomLink> = this.map { it.toEntity() }