package com.ateca.data.local.room

import com.ateca.data.local.room.model.RoomLink
import com.ateca.data.local.room.model.RoomNote
import com.ateca.data.local.room.model.RoomTag
import com.ateca.domain.models.Link
import com.ateca.domain.models.Note
import java.util.*

/**
 * Created by dronpascal on 16.05.2022.
 */
internal fun RoomNote.toModel() = Note(
    id = this.id,
    title = this.title,
    text = this.text,
    folder = this.folder,
    archived = this.isArchived,
    createdAt = Date(this.createdAt),
    modifiedAt = Date(this.modifiedAt)
)

internal fun RoomLink.toModel() = Link(
    name = this.name,
    primaryNoteId = this.primaryNoteId,
    linkedNoteId = this.linkedNoteId,
)

@JvmName("toModelRoomNoteList")
internal fun List<RoomNote>.toModel(): List<Note> = this.map { it.toModel() }

@JvmName("toModelRoomLinkList")
internal fun List<RoomLink>.toModel(): List<Link> = this.map { it.toModel() }

@JvmName("toModelRoomTagList")
internal fun List<RoomTag>.toModel(): List<String> = this.map { it.tag }