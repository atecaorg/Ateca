package com.ateca.ui.screens.note_list.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ateca.R
import com.ateca.ui.components.icon.ThemedIcon
import com.ateca.ui.theme.spacing
import com.ateca.ui.util.darker

/**
 * Created by dronpascal on 24.05.2022.
 */
@Composable
fun NoteListBottomBar(
    onDeleteClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(MaterialTheme.spacing.huge)
            .background(MaterialTheme.colors.background.darker(factor = 0.85f)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onDeleteClicked) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ThemedIcon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colors.onBackground
                )
                Text(
                    text = stringResource(id = R.string.delete),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onBackground
                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
    }
}