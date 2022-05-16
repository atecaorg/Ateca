package com.ateca.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ateca.data.local.room.dao.LinkDao
import com.ateca.data.local.room.dao.NoteDao
import com.ateca.data.local.room.dao.TagDao
import com.ateca.data.local.room.model.RoomLink
import com.ateca.data.local.room.model.RoomNote
import com.ateca.data.local.room.model.RoomTag

/**
 * Created by dronpascal on 16.05.2022.
 */
@Database(
    entities = [
        RoomNote::class,
        RoomLink::class,
        RoomTag::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val linkDao: LinkDao
    abstract val tagDao: TagDao
}