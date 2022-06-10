package com.ateca.ui.screens.note_detailed.view.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.components.icon.ThemedIcon
import com.ateca.ui.components.topappbar.ScrollAwareTopAppBar
import com.ateca.ui.screens.note_detailed.veiwmodel.NoteUIMode
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing

/**
 * Created by eugenics on 20.05.2022.
 * Modified by dronpascal on 09.06.2022.
 */
@Composable
fun NoteTopAppBar(
    title: String,
    uiMode: NoteUIMode,
    onTitleValueChange: (String) -> Unit,
    onNavigationButtonClick: () -> Unit,
    onChangeUiMode: () -> Unit,
    isScrollInInitialState: (() -> Boolean)? = null,
) {
    ScrollAwareTopAppBar(
        title = {
            NoteTitleField(
                title = title,
                onValueChange = onTitleValueChange,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationButtonClick
            ) {
                ThemedIcon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onChangeUiMode) {
                ThemedIcon(
                    imageVector = when (uiMode) {
                        NoteUIMode.EditMode -> Icons.Filled.Visibility
                        NoteUIMode.ViewMode -> Icons.Filled.ModeEdit
                    },
                    contentDescription = null
                )
            }
        },
        isScrollInInitialState = isScrollInInitialState
    )
}

@Composable
private fun NoteTitleField(
    title: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    val isSingleLine = true
    BasicTextField(
        modifier = modifier,
        value = title,
        singleLine = isSingleLine,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.h5
            .copy(
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
            ),
        cursorBrush = SolidColor(MaterialTheme.colors.onBackground.copy(alpha = 0.5f)),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.tiny),
                contentAlignment = Alignment.CenterStart
            ) {
                innerTextField()
                if (title.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.note_title_hint),
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.4f)
                    )
                }
            }
        }
    )
}

@Preview(
    name = "NoteTopAppBarLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteTopAppBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteTopAppBarLargeFont",
    fontScale = AppPreviewConstants.PREVIEW_FONT_SCALE,
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun NoteTopAppBarPreview() {
    AtecaTheme {
        NoteTopAppBar(
            title = "",
            onTitleValueChange = {},
            onNavigationButtonClick = {},
            uiMode = NoteUIMode.ViewMode,
            onChangeUiMode = { }
        )
    }
}