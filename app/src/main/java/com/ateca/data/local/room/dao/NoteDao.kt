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
    suspend fun selectAll(): List<RoomNote>

    @Query("SELECT * FROM $TABLE_NAME WHERE $NOTE_ID = :noteId")
    suspend fun select(noteId: NoteId): RoomNote

    @Query("UPDATE $TABLE_NAME SET $ARCHIVED = :isArchived WHERE $NOTE_ID = :id")
    suspend fun setArchived(id: NoteId, isArchived: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg notes: RoomNote)

    @Delete
    suspend fun delete(note: RoomNote)

    @Query("DELETE FROM $TABLE_NAME WHERE $NOTE_ID = :noteId")
    suspend fun deleteByNoteId(noteId: NoteId)
}