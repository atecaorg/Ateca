package com.ateca.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.ateca.domain.models.NoteId

/**
 * Updated by dronpascal on 16.05.2022.
 */
@Entity(
    tableName = RoomTag.TABLE_NAME,
    primaryKeys = [RoomTag.NOTE_ID, RoomTag.TAG],
    foreignKeys = [ForeignKey(
        entity = RoomNote::class,
        parentColumns = [RoomNote.NOTE_ID],
        childColumns = [RoomTag.NOTE_ID],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomTag(
    @ColumnInfo(name = NOTE_ID)
    val noteId: NoteId,

    @ColumnInfo(name = TAG)
    val tag: String
) {

    companion object {

        const val TABLE_NAME = "tags"

        const val NOTE_ID = "note_id"
        const val TAG = "tag"
    }
}