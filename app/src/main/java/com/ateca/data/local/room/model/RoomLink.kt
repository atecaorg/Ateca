package com.ateca.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.ateca.domain.models.NoteId

/**
 * Updated by dronpascal on 16.05.2022.
 */
@Entity(
    tableName = RoomLink.TABLE_NAME,
    primaryKeys = [RoomLink.PRIMARY_NOTE_ID, RoomLink.LINKED_NOTE_ID],
    foreignKeys = [ForeignKey(
        entity = RoomNote::class,
        parentColumns = [RoomNote.NOTE_ID],
        childColumns = [RoomLink.PRIMARY_NOTE_ID],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomLink(
    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = PRIMARY_NOTE_ID)
    val primaryNoteId: NoteId,

    @ColumnInfo(name = LINKED_NOTE_ID)
    val linkedNoteId: NoteId
) {

    companion object {

        const val TABLE_NAME = "links"

        const val NAME = "name"
        const val PRIMARY_NOTE_ID = "primary_note_id"
        const val LINKED_NOTE_ID = "linked_note_id"
    }
}