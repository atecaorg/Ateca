package com.ateca.data.local.database.dao

import androidx.room.*

@Dao
interface TagDao {
    @Query("SELECT * FROM tags")
    fun fetchAllTags(): List<TagDao>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tagDao: TagDao)

    @Delete
    fun deleteTag(tagDao: TagDao)
}