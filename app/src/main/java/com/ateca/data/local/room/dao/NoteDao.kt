package com.ateca.data.local.room.dao

import androidx.room.*
import com.ateca.data.local.room.model.RoomNote
import com.ateca.data.local.room.model.RoomNote.Companion.ARCHIVED
import com.ateca.data.local.room.model.RoomNote.Companion.NOTE_ID
import com.ateca.data.local.room.model.RoomNote.Companion.TABLE_NAME
import com.ateca.data.local.room.model.RoomNote.Companion.TITLE
import com.ateca.domain.constants.NoteConstants.BASE_TITLE
import com.ateca.domain.models.NoteId

/**
 * Updated by dronpascal on 18.05.2022.
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun selectAll(): List<RoomNote>

    @Query("SELECT * FROM $TABLE_NAME WHERE $NOTE_ID = :noteId LIMIT 1")
    suspend fun select(noteId: NoteId): RoomNote

    @Query("UPDATE $TABLE_NAME SET $ARCHIVED = :isArchived WHERE $NOTE_ID = :id")
    suspend fun setArchived(id: NoteId, isArchived: Boolean)

    @Query("SELECT $TITLE FROM $TABLE_NAME WHERE $TITLE LIKE '$BASE_TITLE %'")
    suspend fun selectBaseTitles(): List<String>

    @Query("SELECT $NOTE_ID FROM $TABLE_NAME WHERE $TITLE = :title LIMIT 1")
    suspend fun selectIdByTitle(title: String): NoteId

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg notes: RoomNote)

    @Query("DELETE FROM $TABLE_NAME WHERE $NOTE_ID = :noteId")
    suspend fun deleteByNoteId(noteId: NoteId)
}