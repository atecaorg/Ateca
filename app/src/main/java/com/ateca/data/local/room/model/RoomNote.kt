package com.ateca.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ateca.domain.models.NoteId

/**
 * Created by dronpascal on 16.05.2022.
 */
@Entity(
    tableName = RoomNote.TABLE_NAME,
    primaryKeys = [RoomNote.NOTE_ID]
)
data class RoomNote(
    @ColumnInfo(name = NOTE_ID)
    val id: NoteId,

    @ColumnInfo(name = TITLE)
    val title: String,

    @ColumnInfo(name = TEXT)
    val text: String,

    @ColumnInfo(name = FOLDER)
    val folder: String?,

    @ColumnInfo(name = ARCHIVED)
    val archived: Boolean,

    @ColumnInfo(name = CREATED)
    var createdAt: Long,

    @ColumnInfo(name = MODIFIED)
    var modifiedAt: Long
) {

    companion object {

        const val TABLE_NAME = "notes"

        const val NOTE_ID = "note_id"
        const val TITLE = "title"
        const val TEXT = "text"
        const val FOLDER = "folder"
        const val ARCHIVED = "archived"
        const val CREATED = "created_at"
        const val MODIFIED = "modified_at"
    }
}