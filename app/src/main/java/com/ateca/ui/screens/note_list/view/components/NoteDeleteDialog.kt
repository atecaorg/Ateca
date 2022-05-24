package com.ateca.ui.screens.note_list.view.components

import androidx.compose.foundation.background
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ateca.R

/**
 * Created by dronpascal on 24.05.2022.
 */
@Composable
fun NoteDeleteDialog(
    state: MutableState<Boolean>,
    onConfirmClicked: () -> Unit
) {
    val onDismiss = { state.value = false }
    AlertDialog(
        modifier = Modifier.background(
            color = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.medium
        ),
        title = { Text(stringResource(R.string.delete_notes_title)) },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmClicked()
                    onDismiss()
                }
            ) {
                Text(
                    text = stringResource(R.string.delete),
                    color = MaterialTheme.colors.error
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    )
}