package com.ateca.di.note

import android.content.Context
import com.ateca.data.local.room.NoteDatabase
import com.ateca.data.local.room.dao.LinkDao
import com.ateca.data.local.room.dao.NoteDao
import com.ateca.data.local.room.dao.TagDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by dronpascal on 18.05.2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object NoteDatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDb(
        @ApplicationContext appContext: Context
    ): NoteDatabase = NoteDatabase.build(appContext)

    @Provides
    fun provideNoteDao(noteDb: NoteDatabase): NoteDao = noteDb.noteDao

    @Provides
    fun provideTagDao(noteDb: NoteDatabase): TagDao = noteDb.tagDao

    @Provides
    fun provideLinkDao(noteDb: NoteDatabase): LinkDao = noteDb.linkDao
}