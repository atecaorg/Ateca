package com.ateca.ui.screens.note_detailed.view.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.ui.components.AppPreviewConstants
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
    text: String,
    uiMode: NoteUIMode,
    onNoteTextChanged: (text: String) -> Unit
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
                val root = parser.parse(text)
                MDDocument(root)
            }
            NoteUIMode.EditMode -> {
                NoteTextField(
                    text = text,
                    onValueChange = { value ->
                        onNoteTextChanged(value)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun NoteTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            textColor = MaterialTheme.colors.onBackground
        ),
        modifier = modifier
    )
}

@Preview(
    name = "NoteContentLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Preview(
    name = "NoteContentDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Preview(
    name = "NoteContentLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 300
)
@Composable
private fun NoteContentPreview() {
    AtecaTheme {
        NoteContent(
            text = AppPreviewConstants.text,
            uiMode = NoteUIMode.ViewMode,
        ) {}
    }
}