package com.ateca.ui.theme.mappers

import androidx.compose.material3.Shapes
import androidx.compose.material.Shapes as MD2Shapes

/**
 * Created by dronpascal on 23.05.2022.
 */
internal fun Shapes.toMD2(): MD2Shapes {
    return MD2Shapes(
        small = this.small,
        medium = this.medium,
        large = this.large
    )
}