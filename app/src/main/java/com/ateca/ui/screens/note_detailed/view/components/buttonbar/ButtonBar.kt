/*
 * Copyright (c) 2022. Eugenics
 *
 * Created by Eugene Podzorov;
 * p.eugenics@gmail.com
 */

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteDetailedEvents
import com.ateca.ui.screens.note_detailed.view.components.buttonbar.LinkDialog
import com.ateca.ui.screens.note_detailed.view.components.buttonbar.MarkButton
import com.ateca.ui.screens.note_detailed.view.components.buttonbar.TitleDialog
import com.ateca.ui.screens.note_detailed.view.constants.*
import com.ateca.ui.theme.md2.AtecaTheme



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ButtonBar(
    textValue: TextFieldValue,
    events: (NoteDetailedEvents) -> Unit = {}
) {
    val context = LocalContext.current
    val showLinkDialog = remember { mutableStateOf(false) }
    val linkIsImage = remember { mutableStateOf(false) }
    val showTitleDialog = remember { mutableStateOf(false) }

    if (showLinkDialog.value) {
        LinkDialog(
            onDisMiss = { showLinkDialog.value = false },
            onConfirm = { link, description ->
                val imagePrefix = if (linkIsImage.value) IMAGE_PREFIX else ""
                val linkText = "$imagePrefix[$description]($link)"

                val cursorPosition = textValue.selection.start + linkText.length
                val text = TextFieldValue(
                    text = textValue.text.replaceRange(
                        startIndex = textValue.selection.start,
                        endIndex = textValue.selection.end,
                        replacement = linkText
                    ),
                    selection = TextRange(cursorPosition)
                )
                events(NoteDetailedEvents.UpdateText(text))
                showLinkDialog.value = false
                linkIsImage.value = false
            }
        )
    }

    if (showTitleDialog.value) {
        TitleDialog(
            onDisMiss = { showTitleDialog.value = false },
            onConfirm = { title ->
                val cursorPosition = textValue.selection.start + title.length
                val text = TextFieldValue(
                    text = textValue.text.replaceRange(
                        startIndex = textValue.selection.start,
                        endIndex = textValue.selection.end,
                        replacement = title
                    ),
                    selection = TextRange(cursorPosition)
                )
                events(NoteDetailedEvents.UpdateText(text))
                showTitleDialog.value = false
            }
        )
    }

    Row(
        modifier = Modifier.navigationBarsPadding().imePadding()
            .imeNestedScroll()
            .horizontalScroll(rememberScrollState())
    ) {
        MarkButton(
            textValue = textValue,
            event = events,
            icon = Icons.Filled.Label,
            buttonValue = TAG_SYMBOL
        )

        MarkButton(
            textValue = textValue,
            event = events,
            icon = Icons.Filled.PostAdd,
            buttonValue = LABEL_SYMBOL,
            cursorPositionOffset = -2
        )

        MarkButton(
            textValue = textValue,
            icon = Icons.Filled.Title
        ) {
            showTitleDialog.value = true
        }

        MarkButton(
            textValue = textValue,
            icon = Icons.Filled.FormatBold,
            buttonValue = BOLD_SYMBOL,
            cursorPositionOffset = -2,
            event = events
        )

        MarkButton(
            textValue = textValue,
            icon = Icons.Filled.FormatItalic,
            buttonValue = ITALIC_SYMBOL,
            cursorPositionOffset = -1,
            event = events
        )

        MarkButton(
            textValue = textValue,
            icon = Icons.Filled.Link
        ) {
            showLinkDialog.value = true
        }

        MarkButton(
            textValue = textValue,
            icon = Icons.Filled.Image
        ) {
            linkIsImage.value = true
            showLinkDialog.value = true
        }

        MarkButton(
            textValue = textValue,
            icon = Icons.Filled.DataObject,
            event = events,
            buttonValue = CODE_BLOCK,
            cursorPositionOffset = -4
        )

        MarkButton(
            textValue = textValue,
            icon = Icons.Filled.Settings
        ) {
            Toast.makeText(context, "Settings...", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(
    name = "ButtonBarLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "ButtonBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun ButtonBarPreview() {
    AtecaTheme {
        ButtonBar(
            textValue = TextFieldValue("")
        ) {}
    }
}