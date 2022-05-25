package com.ateca.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.ateca.UserSettings
import com.ateca.data.settings.SettingsDataSourceImpl
import com.ateca.domain.core.SettingsSerializer
import com.ateca.domain.datasource.ISettingsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(application: Application): DataStore<UserSettings> =
        DataStoreFactory.create(
            serializer = SettingsSerializer,
            produceFile = {
                File(application.filesDir, "datastore/settings.pb")
            },
            corruptionHandler = null,
            migrations = emptyList(),
            scope = CoroutineScope(Dispatchers.IO + Job())
        )

    @Provides
    @Singleton
    fun provideSettingsDataSource(dataStore: DataStore<UserSettings>): ISettingsDataSource =
        SettingsDataSourceImpl(dataStore = dataStore)
}