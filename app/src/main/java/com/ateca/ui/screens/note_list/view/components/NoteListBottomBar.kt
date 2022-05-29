package com.ateca.ui.screens.note_list.view.components

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.components.icon.ThemedIcon
import com.ateca.ui.theme.md2.AtecaTheme
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

@Preview(
    name = "NoteListBottomBarLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteListBottomBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteListBottomBarLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun NoteListBottomBarPreview() {
    AtecaTheme {
        NoteListBottomBar(onDeleteClicked = {})
    }
}