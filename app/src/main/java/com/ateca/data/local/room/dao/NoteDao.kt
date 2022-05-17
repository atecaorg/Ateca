package com.ateca.data.local.room.dao

import androidx.room.*
import com.ateca.data.local.room.model.RoomNote
import com.ateca.data.local.room.model.RoomNote.Companion.ARCHIVED
import com.ateca.data.local.room.model.RoomNote.Companion.NOTE_ID
import com.ateca.data.local.room.model.RoomNote.Companion.TABLE_NAME
import com.ateca.domain.models.NoteId

@Dao
interface NoteDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun selectAll(): List<RoomNote>

    @Query("SELECT * FROM $TABLE_NAME WHERE $NOTE_ID = :noteId")
    fun select(noteId: NoteId): List<RoomNote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg links: RoomNote)

    fun insertWithTimestamp(note: RoomNote) {
        insert(note.apply {
            createdAt = System.currentTimeMillis()
            modifiedAt = System.currentTimeMillis()
        })
    }

    @Update
    fun update(vararg notes: RoomNote)

    fun updateWithTimestamp(note: RoomNote) {
        insert(note.apply {
            modifiedAt = System.currentTimeMillis()
        })
    }

    @Query("UPDATE $TABLE_NAME SET $ARCHIVED = :isArchived WHERE $NOTE_ID = :id")
    fun setArchived(id: NoteId, isArchived: Boolean)

    @Delete
    fun delete(link: RoomNote)

    @Query("DELETE FROM $TABLE_NAME WHERE $NOTE_ID = :noteId")
    fun deleteByNoteId(noteId: NoteId)
}