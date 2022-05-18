package com.ateca.ui.screens.note_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ateca.R
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun NoteTopAppBar(
    paddingValues: PaddingValues,
    title: String,
    onNavigationButtonClick: () -> Unit,
    onSettingButtonClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h5
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
                onClick = onSettingButtonClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_menu),
                    contentDescription = null
                )
            }
        },
        elevation = 0.dp,
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
    )
}

@Preview(backgroundColor = 0xFF00FF00, showBackground = true)
@Composable
private fun NoteTopAppBarPreview() {
    NoteTopAppBar(
        rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyBottom = false
        ),
        title = "SpaceX",
        onNavigationButtonClick = {},
        onSettingButtonClick = {}
    )
}