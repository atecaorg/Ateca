package com.ateca.ui.screens.mock_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MockScreen(screenTitle: String) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = screenTitle,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}