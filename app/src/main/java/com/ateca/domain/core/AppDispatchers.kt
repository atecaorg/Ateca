package com.ateca.domain.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

/**
 * Created by dronpascal on 07.06.2022.
 */
class AppDispatchers(
    override val main: MainCoroutineDispatcher = Dispatchers.Main,
    override val mainImmediate: MainCoroutineDispatcher = Dispatchers.Main.immediate,
    override val default: CoroutineDispatcher = Dispatchers.Default,
    override val io: CoroutineDispatcher = Dispatchers.IO,
    override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
) : IAppDispatchers

interface IAppDispatchers {
    val main: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}