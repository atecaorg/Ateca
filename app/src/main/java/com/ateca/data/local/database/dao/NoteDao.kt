package com.ateca.data.local.database.dao

import androidx.room.*
import com.ateca.data.local.database.pojo.NotePojo

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun fetchAllNotes(): List<NotePojo>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE :noteTitle")
    fun fetchNotesByTitle(noteTitle: String): List<NotePojo>

    @Query("SELECT * FROM notes WHERE noteText LIKE :noteText")
    fun fetchNotesByText(noteText: String): List<NotePojo>

    @Query("SELECT * FROM notes WHERE noteStatusUID = :noteStatusUID")
    fun fetchNotesByStatus(noteStatusUID: String): List<NotePojo>

    @Query(
        "SELECT n.* FROM notes n JOIN notes_tags nt ON nt.noteUID = n.noteUID" +
                " WHERE nt.tagUID = :tagUID"
    )
    fun fetchNotesByTag(tagUID: String): List<NotePojo>

    @Query(
        "SELECT n.* FROM notes n JOIN notes_refs nr ON nr.refNoteUID = n.noteUID" +
                " WHERE nr.noteUID = :noteUID"
    )
    fun fetchLinkedNotes(noteUID: String): List<NotePojo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NotePojo)

    @Delete
    fun deleteNote(note: NotePojo)
}