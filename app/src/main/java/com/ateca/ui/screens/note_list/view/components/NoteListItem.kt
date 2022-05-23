package com.ateca.ui.screens.note_list.view.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ateca.domain.models.Note
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.screens.note_list.view.constants.NoteListItemPreviewConstants
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing
import com.ateca.ui.util.getFormatDate

/**
 * Created by dronpascal on 20.05.2022.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListItem(
    noteItem: Note,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onNoteClicked: (Note) -> Unit,
    onNoteLongPress: (Note) -> Unit,
) {
    Box(modifier = modifier) {
        val backgroundColor: Color = if (isSelected) {
            MaterialTheme.colors.onSurface.copy(alpha = 0.05f)
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = 0.025f)
        }
        val shape = MaterialTheme.shapes.large
        val borderWidth = if (isSelected) 1.dp else 0.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .border(
                    width = borderWidth,
                    color = MaterialTheme.colors.surface,
                    shape = shape
                )
                .clip(shape)
                .combinedClickable(
                    onClick = { onNoteClicked(noteItem) },
                    onLongClick = { onNoteLongPress(noteItem) }
                )
                .padding(MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.tiny)
        ) {
            val titleIsNotBlank = noteItem.title.isNotBlank()
            if (titleIsNotBlank) {
                Text(
                    text = noteItem.title,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (noteItem.text.isNotBlank()) {
                Text(
                    text = noteItem.text,
                    color = MaterialTheme.colors.onBackground.run {
                        if (titleIsNotBlank) copy(alpha = 0.4f)
                        else this
                    },
                    style = MaterialTheme.typography.body1,
                    fontWeight = when {
                        titleIsNotBlank -> FontWeight.Normal
                        else -> FontWeight.Bold
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                //modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                text = getFormatDate(noteItem.modifiedAt),
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
                style = MaterialTheme.typography.body2,
                maxLines = 1
            )
        }
    }
}

@Preview(
    name = "ItemLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "ItemDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun NoteListItemPreview() {
    AtecaTheme {
        NoteListItem(
            noteItem = Note(
                title = "Lorem ipsum",
                text = "lorem ipsum ".repeat(10).replaceFirst("l", "L")
            ),
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            isSelected = false,
            onNoteClicked = {},
            onNoteLongPress = {}
        )
    }
}

@Preview(
    name = "SelectedItemLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SelectedItemDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SearchBarLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun SelectedNoteListItemPreview() {
    AtecaTheme {
        NoteListItem(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            noteItem = NoteListItemPreviewConstants.item,
            isSelected = true,
            onNoteClicked = {},
            onNoteLongPress = {}
        )
    }
}