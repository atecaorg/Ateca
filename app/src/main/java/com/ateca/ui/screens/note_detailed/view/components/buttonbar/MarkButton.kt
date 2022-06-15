/*
 * Copyright (c) 2022. Eugenics
 *
 * Created by Eugene Podzorov;
 * p.eugenics@gmail.com
 */

package com.ateca.ui.screens.note_detailed.view.components.buttonbar

import android.content.res.Configuration
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.components.icon.ThemedIcon
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteDetailedEvents
import com.ateca.ui.theme.md2.AtecaTheme

@Composable
fun MarkButton(
    buttonValue: String = "",
    icon: ImageVector = Icons.Filled.Settings,
    textValue: TextFieldValue,
    cursorPositionOffset: Int = 0,
    event: (NoteDetailedEvents) -> Unit = {}
) {
    IconButton(
        onClick = {
            val cursorPosition = textValue.selection.start + buttonValue.length
            val text = TextFieldValue(
                text = textValue.text.replaceRange(
                    startIndex = textValue.selection.start,
                    endIndex = textValue.selection.end,
                    buttonValue
                ),
                selection = TextRange(cursorPosition + cursorPositionOffset)
            )
            event(NoteDetailedEvents.UpdateText(text))
        }
    ) {
        ThemedIcon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Preview(
    name = "MarkButtonLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "MarkButtonDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun MarkButtonPreview() {
    AtecaTheme {
        MarkButton(
            buttonValue = "#",
            icon = Icons.Filled.Label,
            textValue = TextFieldValue("")
        ) {}
    }
}