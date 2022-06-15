/*
 * Copyright (c) 2022. Eugenics
 *
 * Created by Eugene Podzorov;
 * p.eugenics@gmail.com
 */

package com.ateca.ui.screens.note_detailed.view.components.buttonbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ateca.R
import com.ateca.ui.screens.note_detailed.view.constants.TITLE_L1
import com.ateca.ui.screens.note_detailed.view.constants.TITLE_L2
import com.ateca.ui.screens.note_detailed.view.constants.TITLE_L3
import com.ateca.ui.screens.note_detailed.view.constants.TITLE_L4
import com.ateca.ui.theme.spacing

@Composable
fun TitleDialog(
    onDisMiss: () -> Unit = {},
    onConfirm: (title: String) -> Unit = { _ -> }
) {
    var titleText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDisMiss,
        text =
        {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = stringResource(R.string.title_level),
                    style = MaterialTheme.typography.h5
                )
                Row(Modifier.clickable {
                    titleText = TITLE_L1
                    onConfirm(titleText)
                }
                    .padding(MaterialTheme.spacing.tiny)
                    .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.title_l1),
                        style = MaterialTheme.typography.body1
                    )
                }

                Divider(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
                )

                Row(Modifier.clickable {
                    titleText = TITLE_L2
                    onConfirm(titleText)
                }
                    .padding(MaterialTheme.spacing.tiny)
                    .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.title_l2),
                        style = MaterialTheme.typography.body1
                    )
                }

                Divider(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
                )

                Row(Modifier.clickable {
                    titleText = TITLE_L3
                    onConfirm(titleText)
                }
                    .padding(MaterialTheme.spacing.tiny)
                    .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.title_l3),
                        style = MaterialTheme.typography.body1
                    )
                }

                Divider(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
                )

                Row(Modifier.clickable {
                    titleText = TITLE_L4
                    onConfirm(titleText)
                }
                    .padding(MaterialTheme.spacing.tiny)
                    .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.title_l4),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        },
        confirmButton = { },
        dismissButton =
        {
            TextButton(
                onClick = onDisMiss,
            ) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )
}