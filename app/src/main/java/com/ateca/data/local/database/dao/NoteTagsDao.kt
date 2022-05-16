package com.ateca.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface NoteTagsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNoteTag(noteTagDao: TagDao)

    @Delete
    fun deleteNoteTagDao(noteTagDao: TagDao)
}