package com.ateca.di.coroutine

import com.ateca.domain.core.AppDispatchers
import com.ateca.domain.core.IAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by dronpascal on 10.06.2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppDispatchersModule {

    @Provides
    @Singleton
    fun provideAppDispatchers(): IAppDispatchers = AppDispatchers()
}