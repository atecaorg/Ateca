/*
 * Created by Eugene Podzorov;
 * p.eugenics@gmail.com
 */

package com.ateca.ui.screens.note_detailed.view.components.buttonbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ateca.R

@Composable
fun LinkDialog(
    onDisMiss: () -> Unit = {},
    onConfirm: (link: String, description: String) -> Unit = { _, _ -> }
) {
    var linkText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDisMiss,
        text = {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = stringResource(R.string.link),
                    style = MaterialTheme.typography.h5
                )

                OutlinedTextField(
                    value = linkText,
                    onValueChange = {
                        linkText = it
                    },
                    label = { Text(text = stringResource(R.string.link)) },
                    singleLine = true
                )
                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = {
                        descriptionText = it
                    },
                    label = { Text(text = stringResource(R.string.link_description)) },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(linkText, descriptionText) },
            ) {
                Text(text = stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDisMiss,
            ) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )
}