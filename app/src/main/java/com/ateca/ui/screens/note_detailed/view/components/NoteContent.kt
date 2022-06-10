package com.ateca.ui.screens.note_detailed.view.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteDetailedEvents
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteUIMode
import com.ateca.ui.screens.note_detailed.view.components.markdown.MDDocument
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet

/**
 * Created by eugenics on 20.05.2022.
 * Modified by dronpascal on 09.06.2022.
 */
@Composable
fun NoteContent(
    textValue: TextFieldValue,
    events: (NoteDetailedEvents) -> Unit,
    uiMode: NoteUIMode,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.medium)
            .verticalScroll(rememberScrollState())
    ) {
        when (uiMode) {
            NoteUIMode.ViewMode -> {
                val options = MutableDataSet()
                options.set(Parser.EXTENSIONS, listOf(WikiLinkExtension.create()))
                val parser = Parser.builder(options).build()
                val root = parser.parse(textValue.text)
                MDDocument(root)
            }
            NoteUIMode.EditMode -> {
                NoteTextField(
                    textValue = textValue,
                    events = events,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
private fun NoteTextField(
    modifier: Modifier = Modifier,
    textValue: TextFieldValue,
    events: (NoteDetailedEvents) -> Unit = {},
) {
    BasicTextField(
        modifier = modifier,
        value = textValue,
        onValueChange = { textFieldValue ->
            events(NoteDetailedEvents.UpdateText(textFieldValue))
        },
        textStyle = MaterialTheme.typography.body1
            .copy(color = MaterialTheme.colors.onBackground),
        cursorBrush = SolidColor(MaterialTheme.colors.onBackground.copy(alpha = 0.5f)),
        decorationBox = { innerTextField ->
            Box {
                innerTextField()
                if (textValue.text.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.note_text_hint),
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f)
                    )
                }
            }
        }
    )

}

@Preview(
    name = "NoteContentHintLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Composable
private fun NoteContentHintPreview() {
    AtecaTheme {
        NoteContent(
            textValue = TextFieldValue(""),
            uiMode = NoteUIMode.EditMode,
            events = {}
        )
    }
}

@Preview(
    name = "NoteContentLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Preview(
    name = "NoteContentDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Preview(
    name = "NoteContentLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Composable
private fun NoteContentPreview() {
    AtecaTheme {
        NoteContent(
            textValue = TextFieldValue(AppPreviewConstants.text),
            uiMode = NoteUIMode.EditMode,
            events = {}
        )
    }
}