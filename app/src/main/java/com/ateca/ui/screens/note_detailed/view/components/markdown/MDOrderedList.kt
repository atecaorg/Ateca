package com.ateca.ui.screens.note_detailed.view.components.markdown

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import com.vladsch.flexmark.ast.OrderedList

/**
 * Created by st989-ks on 02.06.2022.
 * Modified by dronpascal on 09.06.2022.
 */
@Composable
fun MDOrderedList(
    orderedList: OrderedList,
    modifier: Modifier = Modifier
) {
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