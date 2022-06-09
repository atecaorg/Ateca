package com.ateca.ui.screens.note_screen.view.components.markdown

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.theme.md2.AtecaTheme
import com.vladsch.flexmark.ast.Image
import com.vladsch.flexmark.util.sequence.BasedSequence

/**
 * Created by st989-ks on 02.06.2022.
 * Modified by dronpascal on 09.06.2022.
 */
@Composable
fun MDImage(
    image: Image,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        var painter: Painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = image.url.normalizeEOL())
                .apply(block = fun ImageRequest.Builder.() { size(Size.ORIGINAL) })
                .build()
        )
        if (LocalInspectionMode.current) {
            painter = painterResource(id = R.drawable.placeholder)
        }
        Image(
            painter,
            contentDescription = null
        )
    }
}

@Preview(
    name = "MDImageLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Preview(
    name = "MDImageDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Preview(
    name = "MDImageLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
)
@Composable
private fun MDImagePreview() {
    AtecaTheme {
        MDImage(
            image = Image(
                BasedSequence.EMPTY,
                BasedSequence.EMPTY,
                BasedSequence.EMPTY,
                BasedSequence.EMPTY,
                BasedSequence.of("file:///android_asset/serios.jpg"),
                BasedSequence.EMPTY
            )
        )
    }
}