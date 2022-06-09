package com.ateca.ui.screens.note_detailed.view.components.markdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.util.ast.Document

/**
 * Created by st989-ks on 02.06.2022.
 * Modified by dronpascal on 09.06.2022.
 */
@Composable
fun MDFencedCodeBlock(
    fencedCodeBlock: FencedCodeBlock,
    modifier: Modifier = Modifier
) {
    val padding = if (fencedCodeBlock.parent is Document) 8.dp else 0.dp
    Box(modifier = modifier.padding(start = 8.dp, bottom = padding)) {
        Text(
            text = fencedCodeBlock.contentChars.normalizeEOL(),
            style = TextStyle(fontFamily = FontFamily.Monospace, color = Color.Red),
            modifier = modifier
        )
    }
}