package com.ateca.ui.screens.settings.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsCard(
    cardTitleText: String,
    cardText: String,
    cardTextColor: Color,
    onCardClick: () -> Unit
) {
    Text(
        text = cardTitleText,
        color = cardTextColor,
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp),
        style = MaterialTheme.typography.body1
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        border = BorderStroke(0.dp, Color.Transparent),
        onClick = onCardClick,
    ) {
        Text(
            text = cardText,
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            style = MaterialTheme.typography.body1
        )
    }
}