package com.ateca.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ateca.R

/**
 * Created by dronpascal on 19.05.2022.
 */
@Composable
fun StubPopup(
    state: MutableState<Boolean>
) {
    val onDismiss = { state.value = false }
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.background(
            color = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.medium
        ),
        text = {
            Text(
                text = stringResource(id = R.string.stub_popup),
                fontWeight = FontWeight.Medium
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.close))
            }
        }
    )
}