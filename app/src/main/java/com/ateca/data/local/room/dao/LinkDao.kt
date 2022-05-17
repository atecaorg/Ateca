package com.ateca.data.local.room.dao

import androidx.room.*
import com.ateca.data.local.room.model.RoomLink
import com.ateca.data.local.room.model.RoomLink.Companion.LINKED_NOTE_ID
import com.ateca.data.local.room.model.RoomLink.Companion.PRIMARY_NOTE_ID
import com.ateca.data.local.room.model.RoomLink.Companion.TABLE_NAME
import com.ateca.domain.models.NoteId

@Dao
interface LinkDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun selectAll(): List<RoomLink>

    @Query("SELECT * FROM $TABLE_NAME WHERE $PRIMARY_NOTE_ID = :primaryNoteId")
    suspend fun select(primaryNoteId: NoteId): List<RoomLink>

    @Query("SELECT * FROM $TABLE_NAME WHERE $LINKED_NOTE_ID = :linkedNoteId")
    suspend fun selectBacklinks(linkedNoteId: NoteId): List<RoomLink>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg links: RoomLink)

    @Delete
    suspend fun delete(link: RoomLink)

    @Query("DELETE FROM $TABLE_NAME WHERE $PRIMARY_NOTE_ID = :noteId")
    suspend fun deleteByNoteId(noteId: NoteId)
}