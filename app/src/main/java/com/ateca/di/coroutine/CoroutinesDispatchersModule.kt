package com.ateca.di.coroutine

import com.ateca.di.qualifiers.*
import com.ateca.domain.core.IAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by dronpascal on 10.06.2022.
 */
@InstallIn(SingletonComponent::class)
@Module
object CoroutinesDispatchersModule {

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(
        appDispatchers: IAppDispatchers
    ): CoroutineDispatcher = appDispatchers.default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(
        appDispatchers: IAppDispatchers
    ): CoroutineDispatcher = appDispatchers.io

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(
        appDispatchers: IAppDispatchers
    ): CoroutineDispatcher = appDispatchers.main

    @UnconfinedDispatcher
    @Provides
    fun providesUnconfinedDispatcher(
        appDispatchers: IAppDispatchers
    ): CoroutineDispatcher = appDispatchers.unconfined

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(
        appDispatchers: IAppDispatchers
    ): CoroutineDispatcher = appDispatchers.mainImmediate
}