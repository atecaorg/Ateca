package com.ateca.data.local.database.dao

import androidx.room.*

@Dao
interface NoteStatusDao {
    @Query("SELECT * FROM notes_status")
    fun fetchAllNotesStatus(): List<NoteStatusDao>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotesStatus(noteStatusDao: NoteStatusDao)

    @Delete
    fun deleteNotesStatus(noteStatusDao: NoteStatusDao)
}