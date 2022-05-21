package com.ateca.ui.components.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.Queue
import com.ateca.domain.core.UIComponent
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.dialog.GenericDialog
import com.ateca.ui.components.progress.CircularIndeterminateProgressBar
import com.ateca.ui.theme.md2.AtecaTheme

/**
 * Created by dronpascal on 20.05.2022.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DefaultScreenUI(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    queue: Queue<UIComponent> = Queue(mutableListOf()),
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    onRemoveHeadFromQueue: () -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    ) {
        content()
        if (!queue.isEmpty()) {
            queue.peek()?.let { uiComponent ->
                if (uiComponent is UIComponent.Dialog) {
                    GenericDialog(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        title = uiComponent.title.asString(),
                        description = uiComponent.description.asString(),
                        onRemoveHeadFromQueue = onRemoveHeadFromQueue
                    )
                }
            }
        }
        if (progressBarState is ProgressBarState.Loading) {
            CircularIndeterminateProgressBar()
        }
    }
}

@Preview(
    name = "DefaultScreenUILight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "DefaultScreenUIDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun DefaultScreenUIPreview() {
    AtecaTheme {
        DefaultScreenUI(
            onRemoveHeadFromQueue = {},
            content = { }
        )
    }
}
