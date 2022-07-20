package com.ateca.ui.screens.note_detailed.view.components.markdown

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.screens.note_detailed.view.constants.MDTags.TAG_IMAGE_URL
import com.ateca.ui.screens.note_detailed.view.constants.MDTags.TAG_NOTE_LINK
import com.ateca.ui.screens.note_detailed.view.constants.MDTags.TAG_URL
import com.ateca.ui.theme.md2.AtecaTheme
import kotlinx.coroutines.launch

/**
 * Created by st989-ks on 02.06.2022.
 * Modified by dronpascal on 09.06.2022.
 */
@Composable
fun MarkdownText(
    text: AnnotatedString,
    style: TextStyle,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(text = text,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { offset ->
                layoutResult.value?.let { layoutResult ->
                    val position = layoutResult.getOffsetForPosition(offset)
                    text.getStringAnnotations(position, position)
                        .firstOrNull()
                        ?.let { sa ->
                            when (sa.tag){
                                in TAG_URL -> uriHandler.openUri(sa.item)
                                in TAG_NOTE_LINK -> {
                                    // TODO Add App link
                                    Log.d(TAG_NOTE_LINK, "${sa.item}")
                                }
                            }

                        }
                }
            }
        },
        style = style.copy(color = MaterialTheme.colors.onBackground),
        inlineContent = mapOf(
            TAG_IMAGE_URL to InlineTextContent(
                Placeholder(style.fontSize, style.fontSize, PlaceholderVerticalAlign.Bottom)
            ) {
                var painter: Painter = rememberAsyncImagePainter(model = it)
                if (LocalInspectionMode.current) {
                    painter = painterResource(id = R.drawable.placeholder)
                }
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = modifier,
                    alignment = Alignment.Center
                )
            }
        ),
        onTextLayout = { layoutResult.value = it }
    )
}

@Preview(
    name = "MarkdownTextLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Preview(
    name = "MarkdownTextDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Preview(
    name = "MarkdownTextLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun MarkdownTextPreview() {
    AtecaTheme {
        MarkdownText(
            text = buildAnnotatedString { append(AppPreviewConstants.text) },
            style = TextStyle()
        )
    }
}
