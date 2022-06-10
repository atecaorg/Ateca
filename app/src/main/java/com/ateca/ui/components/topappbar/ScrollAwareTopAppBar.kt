package com.ateca.ui.components.topappbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.theme.md2.AtecaTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by dronpascal on 20.05.2022.
 */
@Composable
fun ScrollAwareTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    isScrollInInitialState: (() -> Boolean)? = null,
) {

    val isScrolledFromIdle: Boolean = isScrollInInitialState?.invoke() == false

    val systemUiController = rememberSystemUiController()
    val isLightTheme = MaterialTheme.colors.isLight
    val statusBarColor = if (!isLightTheme && isScrolledFromIdle) {
        MaterialTheme.colors.surface
    } else {
        MaterialTheme.colors.background
    }

    LaunchedEffect(key1 = isScrolledFromIdle) {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = isLightTheme
        )
    }

    val toolbarElevation: Dp = if (isLightTheme) {
        if (isScrolledFromIdle) {
            AppBarDefaults.TopAppBarElevation
        } else {
            0.dp
        }
    } else {
        0.dp
    }

    val backgroundColor: Color = if (!isLightTheme && !isScrolledFromIdle) {
        MaterialTheme.colors.background
    } else {
        MaterialTheme.colors.surface
    }
    val contentColor = contentColorFor(backgroundColor)

    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = toolbarElevation
    )


}

@Preview(
    name = "ScrollAwareTopAppBarLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "ScrollAwareTopAppBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun ScrollAwareTopAppBarPreview() {
    AtecaTheme {
        ScrollAwareTopAppBar()
    }
}