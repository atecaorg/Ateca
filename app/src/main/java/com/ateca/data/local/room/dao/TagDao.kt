package com.ateca.data.local.room.dao

import androidx.room.*
import com.ateca.data.local.room.model.RoomTag
import com.ateca.data.local.room.model.RoomTag.Companion.NOTE_ID
import com.ateca.data.local.room.model.RoomTag.Companion.TABLE_NAME
import com.ateca.domain.models.NoteId

@Dao
interface TagDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun selectAll(): List<RoomTag>

    @Query("SELECT DISTINCT * FROM $TABLE_NAME")
    suspend fun selectDistinct(): List<RoomTag>

    @Query("SELECT * FROM $TABLE_NAME WHERE $NOTE_ID = :noteId")
    suspend fun select(noteId: NoteId): List<RoomTag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg tags: RoomTag)

    @Delete
    suspend fun delete(tag: RoomTag)

    @Query("DELETE FROM $TABLE_NAME WHERE $NOTE_ID = :noteId")
    suspend fun deleteByNoteId(noteId: NoteId)
}