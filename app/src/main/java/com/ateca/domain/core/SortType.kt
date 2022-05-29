package com.ateca.domain.core

/**
 * Created by dronpascal on 29.05.2022.
 */
sealed class SortType {

    object Created : SortType()

    object Modified : SortType()

    object Name : SortType()

    object Idle : SortType()
}
