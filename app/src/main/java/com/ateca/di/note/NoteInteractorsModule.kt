package com.ateca.di.note

import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.entity.IMarkdownProcessor
import com.ateca.domain.entity.MarkdownProcessor
import com.ateca.domain.interactors.NoteInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by dronpascal on 18.05.2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object NoteInteractorsModule {

    @Provides
    @Singleton
    fun provideMarkdownProcessor(): IMarkdownProcessor = MarkdownProcessor()

    @Provides
    @Singleton
    fun provideNoteInteractors(
        noteDataSource: INoteDataSource,
        linkDataSource: ILinkDataSource,
        markdownProcessor: IMarkdownProcessor
    ): NoteInteractors {
        return NoteInteractors.build(
            noteDataSource,
            linkDataSource,
            markdownProcessor
        )
    }
}