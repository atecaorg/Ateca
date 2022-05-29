package com.ateca.domain.core

/**
 * Created by dronpascal on 29.05.2022.
 *
 * A generic class for hiding/showing some ui component.
 */
sealed class UIComponentState {

    object Show : UIComponentState()

    object Hide : UIComponentState()
}
