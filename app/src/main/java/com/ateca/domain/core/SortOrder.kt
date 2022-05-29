package com.ateca.domain.core

/**
 * Created by dronpascal on 29.05.2022.
 */
sealed class SortOrder {

    object Ascending: SortOrder()

    object Descending: SortOrder()

    object Idle: SortOrder()
}
