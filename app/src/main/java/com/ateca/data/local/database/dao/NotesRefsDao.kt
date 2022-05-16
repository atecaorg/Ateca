package com.ateca.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface NotesRefsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotesRef(notesRefsDao: NotesRefsDao)

    @Delete
    fun deleteNotesRef(notesRefsDao: NotesRefsDao)
}