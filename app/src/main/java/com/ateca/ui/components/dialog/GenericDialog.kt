package com.ateca.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by dronpascal on 20.05.2022.
 */
@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    onRemoveHeadFromQueue: () -> Unit,
) {
    AlertDialog(
        modifier = modifier.background(
            color = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.medium
        ),
        onDismissRequest = {
            onRemoveHeadFromQueue()
        },
        title = {
            Text(
                text = title
            )
        },
        text = {
            if (description != null) {
                Text(text = description)
            }
        },
        buttons = {}
    )
}