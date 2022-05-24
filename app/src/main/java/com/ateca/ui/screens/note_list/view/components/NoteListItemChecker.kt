package com.ateca.ui.screens.note_list.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ateca.ui.components.icon.ThemedIcon

/**
 * Created by dronpascal on 23.05.2022.
 */
@Composable
fun NoteListItemChecker(
    isSelected: Boolean,
    isInSelectMode: Boolean,
    colorInSelectMode: Color,
    modifier: Modifier = Modifier
) {
    val checkerBackgroundColor = when {
        isSelected -> MaterialTheme.colors.primary
        !isSelected && isInSelectMode -> colorInSelectMode
        else -> Color.Transparent
    }
    val iconTint = when (isSelected) {
        true -> MaterialTheme.colors.onPrimary
        false -> Color.Transparent
    }
    ThemedIcon(
        modifier = modifier
            .background(checkerBackgroundColor, CircleShape),
        imageVector = Icons.Filled.Check,
        tint = iconTint,
        contentDescription = "Selected"
    )
}