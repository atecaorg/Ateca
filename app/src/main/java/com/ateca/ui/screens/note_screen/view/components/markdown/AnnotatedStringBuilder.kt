package com.ateca.ui.screens.note_screen.view.components.markdown

import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Colors
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.ateca.ui.screens.note_screen.view.constants.MDTags.TAG_IMAGE_URL
import com.ateca.ui.screens.note_screen.view.constants.MDTags.TAG_URL
import com.vladsch.flexmark.ast.*
import com.vladsch.flexmark.ext.wikilink.WikiNode
import com.vladsch.flexmark.util.ast.Node

/**
 * Created by st989-ks on 02.06.2022.
 * Modified by dronpascal on 09.06.2022.
 */
fun AnnotatedString.Builder.appendMarkdownChildren(
    parent: Node,
    colors: Colors
) {
    var child = parent.firstChild
    while (child != null) {
        when (child) {
            is Paragraph -> appendMarkdownChildren(child, colors)
            is Text -> append(child.chars.normalizeEOL())
            is Image -> appendInlineContent(TAG_IMAGE_URL, child.url.normalizeEOL())
            is Emphasis -> appendEmphasis(child, colors)
            is StrongEmphasis -> appendStrongEmphasis(child, colors)
            is Code -> appendCodeBlock(child)
            is HardLineBreak -> append("\n")
            is Link -> appendLink(child, colors)
            is WikiNode -> appendWikiLink(child, colors)
        }
        child = child.next
    }
}

private fun AnnotatedString.Builder.appendEmphasis(
    child: Node,
    colors: Colors
) {
    pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
    appendMarkdownChildren(child, colors)
    pop()
}

private fun AnnotatedString.Builder.appendStrongEmphasis(
    child: Node,
    colors: Colors
) {
    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
    appendMarkdownChildren(child, colors)
    pop()
}

private fun AnnotatedString.Builder.appendCodeBlock(child: Code) {
    pushStyle(TextStyle(fontFamily = FontFamily.Monospace).toSpanStyle())
    append(child.chars.normalizeEOL())
    pop()
}

private fun AnnotatedString.Builder.appendLink(
    child: Link,
    colors: Colors
) {
    val underline = SpanStyle(colors.primary, textDecoration = TextDecoration.Underline)
    pushStyle(underline)
    pushStringAnnotation(TAG_URL, child.url.normalizeEOL())
    appendMarkdownChildren(child, colors)
    pop()
    pop()
}

private fun AnnotatedString.Builder.appendWikiLink(
    child: WikiNode,
    colors: Colors
) {
    val underline = SpanStyle(colors.primary, textDecoration = TextDecoration.Underline)
    pushStyle(underline)
    val (text, link) = child.text to child.link
    val rawUrl = if (link.isNotNull) link else text
    val linkText = if (text.isNotNull) text else link
    // TODO Add App link
    val linkUrl = "ateca://note/${rawUrl.normalizeEOL()}"
    pushStringAnnotation(TAG_URL, rawUrl.normalizeEOL())
    append(linkText.normalizeEOL())
    pop()
    pop()
}