package com.ateca.ui.screens.note_detailed.view.components.markdown

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.screens.note_detailed.view.constants.NotePreviewConstants
import com.ateca.ui.theme.md2.AtecaTheme
import com.vladsch.flexmark.ast.*
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Document
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.data.MutableDataSet

/**
 * Created by st989-ks on 02.06.2022.
 * Modified by dronpascal on 09.06.2022.
 *
 * These functions will render flexmark
 */
@Composable
fun MDDocument(document: Document) {
    MDBlockChildren(document)
}

@Composable
fun MDBlockChildren(parent: Node) {
    var child = parent.firstChild
    while (child != null) {
        when (child) {
            is BlockQuote -> MDBlockQuote(child)
            is ThematicBreak -> MDThematicBreak(child)
            is Heading -> MDHeading(child)
            is Paragraph -> MDParagraph(child)
            is FencedCodeBlock -> MDFencedCodeBlock(child)
            is IndentedCodeBlock -> MDIndentedCodeBlock(child)
            is Image -> MDImage(child)
            is BulletList -> MDBulletList(child)
            is OrderedList -> MDOrderedList(child)
            is HtmlBlock -> MDHtmlBlock(child)
        }
        child = child.next
    }
}

@Composable
private fun MDIndentedCodeBlock(
    indentedCodeBlock: IndentedCodeBlock,
    modifier: Modifier = Modifier
) {
    // Ignored
}

@Composable
private fun MDThematicBreak(
    thematicBreak: ThematicBreak,
    modifier: Modifier = Modifier
) {
    //Ignored
}

@Preview(
    name = "MDDocumentLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Preview(
    name = "MDDocumentDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Preview(
    name = "MDDocumentLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun MDDocumentPreview() {
    AtecaTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            val options = MutableDataSet()
            options.set(Parser.EXTENSIONS, listOf(WikiLinkExtension.create()))
            val parser = Parser.builder(options).build()
            val textThis = NotePreviewConstants.MIXED_MD
            val root = parser.parse(textThis)
            MDDocument(root)
        }
    }
}
