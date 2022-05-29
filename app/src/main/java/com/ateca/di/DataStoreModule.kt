package com.ateca.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.ateca.data.local.datastore.SettingsSerializer
import com.ateca.data.settings.SettingsDataSourceImpl
import com.ateca.domain.datasource.ISettingsDataSource
import com.ateca.domain.models.ApplicationSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        application: Application
    ): DataStore<ApplicationSettings> =
        DataStoreFactory.create(
            serializer = SettingsSerializer,
            produceFile = {
                File(
                    application.filesDir,
                    "datastore/application_settings.pb"
                )
            }
        )

    @Provides
    @Singleton
    fun provideSettingsDataSource(
        dataStore: DataStore<ApplicationSettings>
    ): ISettingsDataSource =
        SettingsDataSourceImpl(dataStore = dataStore)
}