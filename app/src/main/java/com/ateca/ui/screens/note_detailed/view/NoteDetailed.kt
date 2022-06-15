package com.ateca.ui.screens.note_detailed.view

import ButtonBar
import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.domain.models.Note
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.components.screen.DefaultScreenUI
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteDetailedEvents
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteDetailedState
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteUIMode
import com.ateca.ui.screens.note_detailed.view.components.NoteContent
import com.ateca.ui.screens.note_detailed.view.components.NoteTopAppBar
import com.ateca.ui.screens.note_detailed.view.constants.NotePreviewConstants
import com.ateca.ui.theme.md2.AtecaTheme

/**
 * Created by eugenics on 20.05.2022.
 * Modified by dronpascal on 09.06.2022.
 */
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteDetailed(
    state: NoteDetailedState,
    events: (NoteDetailedEvents) -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(NoteDetailedEvents.RemoveHeadFromMessageQueue) },
        progressBarState = state.progressBarState,
    ) {
        Scaffold(
            topBar = {
                NoteTopAppBar(
                    title = state.note.title,
                    uiMode = state.mode,
                    onChangeUiMode = { events(NoteDetailedEvents.ChangeUIMode) },
                    onNavigationButtonClick = {
                        events(NoteDetailedEvents.SaveNote)
                        onNavigateBack.invoke()
                    },
                    onTitleValueChange = { value ->
                        events(NoteDetailedEvents.UpdateTitle(value))
                    }
                )
            },
            bottomBar = {
//                when (state.mode) {
//                    NoteUIMode.EditMode -> {
//                        val text = state.note.text
//                        ButtonBar(
//                            state = state,
//                            events = events
//                        )
//                    }
//                    else -> {}
//                }
            }
        ) {
            NoteContent(
                textValue = state.textValue,
                events = events,
                uiMode = state.mode
            )
        }
    }
}

@Preview(
    name = "NoteDetailedLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Preview(
    name = "NoteDetailedDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Preview(
    name = "NoteDetailedLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Composable
private fun NoteDetailedPreview() {
    AtecaTheme {
        NoteDetailed(
            state = NoteDetailedState(
                note = Note(
                    title = AppPreviewConstants.title,
                    text = NotePreviewConstants.MIXED_MD
                ),
                mode = NoteUIMode.ViewMode
            )
        )
    }
}

@Preview(
    name = "NoteDetailedEditLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Preview(
    name = "NoteDetailedEditDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Preview(
    name = "NoteDetailedEditLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Composable
private fun NoteDetailedEditPreview() {
    AtecaTheme {
        NoteDetailed(
            state = NoteDetailedState(
                note = Note(
                    title = AppPreviewConstants.title,
                    text = NotePreviewConstants.MIXED_MD
                ),
                mode = NoteUIMode.EditMode
            )
        )
    }
}