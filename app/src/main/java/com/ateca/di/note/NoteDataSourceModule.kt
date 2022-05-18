package com.ateca.di.note

import com.ateca.data.local.LinkDataSource
import com.ateca.data.local.NoteDataSource
import com.ateca.data.local.TagDataSource
import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.datasource.ITagDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by dronpascal on 18.05.2022.
 */
@Module
@InstallIn(SingletonComponent::class)
interface NoteDataSourceModule {

    @Binds
    fun bindNoteDataSource(
        impl: NoteDataSource,
    ): INoteDataSource

    @Binds
    fun bindTagDataSource(
        impl: TagDataSource,
    ): ITagDataSource

    @Binds
    fun bindLinkDataSource(
        impl: LinkDataSource,
    ): ILinkDataSource
}
