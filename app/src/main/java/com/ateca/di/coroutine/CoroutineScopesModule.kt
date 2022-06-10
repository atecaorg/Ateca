package com.ateca.di.coroutine

import com.ateca.di.qualifiers.ApplicationScope
import com.ateca.di.qualifiers.DefaultDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Created by dronpascal on 10.06.2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopesModule {

    @Singleton
    @ApplicationScope
    @Provides
    fun providesCoroutineScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope =
        CoroutineScope(SupervisorJob() + defaultDispatcher)
}
