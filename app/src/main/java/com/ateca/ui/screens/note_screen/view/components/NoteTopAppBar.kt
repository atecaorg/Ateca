package com.ateca.ui.screens.note_screen.view.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants
import com.ateca.ui.components.topappbar.ScrollAwareTopAppBar
import com.ateca.ui.theme.md2.AtecaTheme

@Composable
fun NoteTopAppBar(
    title: String,
    onTitleValueChange: (String) -> Unit,
    onNavigationButtonClick: () -> Unit
) {
    val showMenu = remember { mutableStateOf(false) }
    val visibilityState = remember { mutableStateOf(false) }

    ScrollAwareTopAppBar(
        title = {
            NoteTitleField(
                title = title,
                onValueChange = onTitleValueChange
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationButtonClick
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = { visibilityState.value = !visibilityState.value }) {
                Icon(
                    if (visibilityState.value) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.ModeEdit
                    },
                    contentDescription = null
                )
            }
            IconButton(
                onClick = { showMenu.value = true }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null
                )
            }
            DropdownMenu(
                expanded = showMenu.value,
                onDismissRequest = { showMenu.value = false }
            ) {
                DropdownMenuItem(
                    onClick = { showMenu.value = false }
                ) {
                    Text(text = "Settings")
                }
                DropdownMenuItem(
                    onClick = { showMenu.value = false }
                ) {
                    Text("About")
                }
            }
        }
    )
}

@Composable
private fun NoteTitleField(
    title: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = title,
        singleLine = true,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.h6,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onBackground,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(width = 0.dp, color = Color.Transparent))
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
@Composable
private fun NoteTopAppBarPreview() {
    AtecaTheme {
        NoteTopAppBar(
            title = "SpaceX",
            onNavigationButtonClick = {},
            onTitleValueChange = {}
        )
    }
}

@Preview(
    name = "NoteTitleLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "NoteTitleDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun NoteTitlePreview() {
    AtecaTheme {
        NoteTitleField(title = "Title") {}
    }
}