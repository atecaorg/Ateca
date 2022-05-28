package com.ateca.ui.screens.settings.view.components


import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ateca.R

@Composable
fun SoftwareInfoDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = {
            Button(
                onClick = onDismiss,
                content = {
                    Text(text = stringResource(R.string.close))
                }
            )
        },
        text = {
            Text(
                text = "Ateca team ${'\u00A9'} 2022"
            )
        }
    )
}