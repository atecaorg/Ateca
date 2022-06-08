package com.ateca.ui.screens.note_screen.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.vladsch.flexmark.ast.BlockQuote
import com.vladsch.flexmark.ast.BulletList
import com.vladsch.flexmark.ast.Code
import com.vladsch.flexmark.ast.Emphasis
import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.ast.HardLineBreak
import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.ast.HtmlBlock
import com.vladsch.flexmark.ast.Image
import com.vladsch.flexmark.ast.IndentedCodeBlock
import com.vladsch.flexmark.ast.Link
import com.vladsch.flexmark.ast.ListBlock
import com.vladsch.flexmark.ast.OrderedList
import com.vladsch.flexmark.ast.Paragraph
import com.vladsch.flexmark.ast.StrongEmphasis
import com.vladsch.flexmark.ast.Text
import com.vladsch.flexmark.ast.ThematicBreak
import com.vladsch.flexmark.util.ast.Document
import com.vladsch.flexmark.util.ast.Node

/**
 * These functions will render flexmark
 */

private const val TAG_URL = "url"
private const val TAG_IMAGE_URL = "imageUrl"

@Composable
fun MDDocument(document: Document) {
    MDBlockChildren(document)
}

@Composable
fun MDHeading(heading: Heading, modifier: Modifier = Modifier) {
    val style = when (heading.level) {
        1 -> MaterialTheme.typography.h1
        2 -> MaterialTheme.typography.h2
        3 -> MaterialTheme.typography.h3
        4 -> MaterialTheme.typography.h4
        5 -> MaterialTheme.typography.h5
        6 -> MaterialTheme.typography.h6
        else -> {
            // Invalid header...
            MDBlockChildren(heading)
            return
        }
    }

    val padding = if (heading.parent is Document) 8.dp else 0.dp
    Box(modifier = modifier.padding(bottom = padding)) {
        val text = buildAnnotatedString {
            appendMarkdownChildren(heading, MaterialTheme.colors)
        }
        MarkdownText(text, style)
    }
}

@Composable
fun MDParagraph(paragraph: Paragraph, modifier: Modifier = Modifier) {
    if (paragraph.firstChild is Image && paragraph.firstChild == paragraph.lastChild) {
        // Paragraph with single image
        MDImage(paragraph.firstChild as Image, modifier)
    } else {
        val padding = if (paragraph.parent is Document) 8.dp else 0.dp
        Box(modifier = modifier.padding(bottom = padding)) {
            val styledText = buildAnnotatedString {
                pushStyle(MaterialTheme.typography.body1.toSpanStyle())
                appendMarkdownChildren(paragraph, MaterialTheme.colors)
                pop()
            }
            MarkdownText(styledText, MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun MDImage(image: Image, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(
            painter = rememberImagePainter(
                data = image.url.normalizeEOL(),
                builder = {
                    size(OriginalSize)
                },
            ),
            contentDescription = null,
        )
    }
}

@Composable
fun MDBulletList(bulletList: BulletList, modifier: Modifier = Modifier) {
    val marker = bulletList.openingMarker///////////////?
    MDListItems(bulletList, modifier = modifier) {
        val text = buildAnnotatedString {
            pushStyle(MaterialTheme.typography.body1.toSpanStyle())
            append("$marker ")
            appendMarkdownChildren(it, MaterialTheme.colors)
            pop()
        }
        MarkdownText(text, MaterialTheme.typography.body1, modifier)
    }
}

@Composable
fun MDOrderedList(orderedList: OrderedList, modifier: Modifier = Modifier) {
    var number = orderedList.startNumber
    val delimiter = orderedList.delimiter
    MDListItems(orderedList, modifier) {
        val text = buildAnnotatedString {
            pushStyle(MaterialTheme.typography.body1.toSpanStyle())
            append("${number++}$delimiter ")
            appendMarkdownChildren(it, MaterialTheme.colors)
            pop()
        }
        MarkdownText(text, MaterialTheme.typography.body1, modifier)
    }
}

@Composable
fun MDListItems(
    listBlock: ListBlock,
    modifier: Modifier = Modifier,
    item: @Composable (node: Node) -> Unit
) {
    val bottom = if (listBlock.parent is Document) 8.dp else 0.dp
    val start = if (listBlock.parent is Document) 0.dp else 8.dp
    Column(modifier = modifier.padding(start = start, bottom = bottom)) {
        var listItem = listBlock.firstChild
        while (listItem != null) {
            var child = listItem.firstChild
            while (child != null) {
                when (child) {
                    is BulletList -> MDBulletList(child, modifier)
                    is OrderedList -> MDOrderedList(child, modifier)
                    else -> item(child)
                }
                child = child.next
            }
            listItem = listItem.next
        }
    }
}

@Composable
fun MDBlockQuote(blockQuote: BlockQuote, modifier: Modifier = Modifier) {
    val color = MaterialTheme.colors.onBackground
    Box(modifier = modifier
        .drawBehind {
            drawLine(
                color = color,
                strokeWidth = 2f,
                start = Offset(12.dp.value, 0f),
                end = Offset(12.dp.value, size.height)
            )
        }
        .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)) {
        val text = buildAnnotatedString {
            pushStyle(
                MaterialTheme.typography.body1.toSpanStyle()
                    .plus(SpanStyle(fontStyle = FontStyle.Italic))
            )
            appendMarkdownChildren(blockQuote, MaterialTheme.colors)
            pop()
        }
        Text(text, modifier)
    }
}

@Composable
fun MDIndentedCodeBlock(indentedCodeBlock: IndentedCodeBlock, modifier: Modifier = Modifier) {
    // Ignored
}

@Composable
fun MDThematicBreak(thematicBreak: ThematicBreak, modifier: Modifier = Modifier) {
    //Ignored
}

@Composable
fun MDBlockChildren(parent: Node) {
    var child = parent
        .firstChild
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
            is HtmlBlock -> MDOHtmlBlock(child)
        }
        child = child.next
    }
}



@Composable
fun MDOHtmlBlock(htmlBlock: HtmlBlock, modifier: Modifier = Modifier) {
    val padding = if (htmlBlock.parent is Document) 8.dp else 0.dp
    Box(modifier = modifier.padding(start = 8.dp, bottom = padding)) {
        Text(
            text = htmlBlock.chars.normalizeEOL(),//Проверить?
            style = TextStyle(fontFamily = FontFamily.Monospace, color = Color.Green),
            modifier = modifier
        )
    }
}

@Composable
fun MDFencedCodeBlock(fencedCodeBlock: FencedCodeBlock, modifier: Modifier = Modifier) {
    val padding = if (fencedCodeBlock.parent is Document) 8.dp else 0.dp
    Box(modifier = modifier.padding(start = 8.dp, bottom = padding)) {
        Text(
            text = fencedCodeBlock.chars.normalizeEOL(),//Проверить?
            style = TextStyle(fontFamily = FontFamily.Monospace, color = Color.Red),
            modifier = modifier
        )
    }
}


fun AnnotatedString.Builder.appendMarkdownChildren(
    parent: Node, colors: Colors
) {
    var child = parent.firstChild
    while (child != null) {
        when (child) {
            is Paragraph -> appendMarkdownChildren(child, colors)
            is Text -> append(child.chars.normalizeEOL())
            is Image -> appendInlineContent(TAG_IMAGE_URL, child.url.normalizeEOL())//////+++
            is Emphasis -> {
                pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
                appendMarkdownChildren(child, colors)
                pop()
            }
            is StrongEmphasis -> {
                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                appendMarkdownChildren(child, colors)
                pop()
            }
            is Code -> {
                pushStyle(TextStyle(fontFamily = FontFamily.Monospace).toSpanStyle())
                append(child.chars.normalizeEOL())///////вроде норм
                pop()
            }
            is HardLineBreak -> {
                append("\n")
            }
            is Link -> {
                val underline = SpanStyle(colors.primary, textDecoration = TextDecoration.Underline)
                pushStyle(underline)
                pushStringAnnotation(TAG_URL, child.url.normalizeEOL())//+++
                appendMarkdownChildren(child, colors)
                pop()
                pop()
            }
        }
        child = child.next
    }
}

@Composable
fun MarkdownText(text: AnnotatedString, style: TextStyle, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    Text(text = text,
        modifier.pointerInput(Unit) {
            detectTapGestures { offset ->
                layoutResult.value?.let { layoutResult ->
                    val position = layoutResult.getOffsetForPosition(offset)
                    text.getStringAnnotations(position, position)
                        .firstOrNull()
                        ?.let { sa ->
                            if (sa.tag == TAG_URL) {
                                uriHandler.openUri(sa.item)
                            }
                        }
                }
            }
        },
        style = style,
        inlineContent = mapOf(
            TAG_IMAGE_URL to InlineTextContent(
                Placeholder(style.fontSize, style.fontSize, PlaceholderVerticalAlign.Bottom)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = it,
                    ),
                    contentDescription = null,
                    modifier = modifier,
                    alignment = Alignment.Center
                )

            }
        ),
        onTextLayout = { layoutResult.value = it }
    )
}