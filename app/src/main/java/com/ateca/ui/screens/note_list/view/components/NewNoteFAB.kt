package com.ateca.ui.screens.note_list.view.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ateca.ui.components.icon.ThemedIcon

/**
 * Created by dronpascal on 23.05.2022.
 */
@Composable
fun NewNoteFAB(
    onClick: () -> Unit,
) {
    val elevation = when (MaterialTheme.colors.isLight) {
        true -> FloatingActionButtonDefaults.elevation()
        false -> FloatingActionButtonDefaults.elevation()
    }
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = elevation
    ) {
        ThemedIcon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add",
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(32.dp)
        )
    }
}
