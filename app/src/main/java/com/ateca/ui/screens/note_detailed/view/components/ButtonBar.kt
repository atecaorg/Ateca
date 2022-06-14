import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ateca.ui.components.AppPreviewConstants

/*
 * Copyright (c) 2022. Eugenics
 *
 * Created by Eugene Podzorov;
 * Licensed under the Apache License, Version 2.0 (the "License");
 * p.eugenics@gmail.com
 */

@Composable
fun ButtonBar() {
    LazyRow {
        Button(
            onClick = {}
        ) {
            Text(text = "Button1")
        }
        Button(
            onClick = {}
        ) {
            Text(text = "Button2")
        }
        Button(
            onClick = {}
        ) {
            Text(text = "Button3")
        }
        Button(
            onClick = {}
        ) {
            Text(text = "Button4")
        }
    }
}

@Preview(
    name = "ButtonBarLight",
    backgroundColor = AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Preview(
    name = "ButtonBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true,
    widthDp = 400,
    heightDp = 150
)
@Composable
private fun ButtonBarPreview() {
    ButtonBar()
}