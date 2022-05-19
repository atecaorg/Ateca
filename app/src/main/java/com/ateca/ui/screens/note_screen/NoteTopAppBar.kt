package com.ateca.ui.screens.note_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ateca.R

@Composable
fun NoteTopAppBar(
    title: String,
    onTitleValueChange: (String) -> Unit,
    onNavigationButtonClick: () -> Unit
) {
    val showMenu = remember { mutableStateOf(false) }

    TopAppBar(
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
                    painter = painterResource(R.drawable.ic_icon_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(
                onClick = { showMenu.value = true }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_menu),
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
        },
        elevation = 0.dp
    )
}

@Preview(backgroundColor = 0xFF00FF00, showBackground = true)
@Composable
private fun NoteTopAppBarPreview() {
    NoteTopAppBar(
        title = "SpaceX",
        onNavigationButtonClick = {},
        onTitleValueChange = {}
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
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(width = 0.dp, color = Color.Transparent))
    )
}

@Preview(backgroundColor = 0xFF00FF00, showBackground = true)
@Composable
private fun NoteTitlePreview() {
    NoteTitleField(title = "Title") {}
}