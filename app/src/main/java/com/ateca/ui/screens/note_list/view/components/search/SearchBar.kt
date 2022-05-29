package com.ateca.ui.screens.note_list.view.components.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ateca.R
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_DARK_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_FONT_SCALE
import com.ateca.ui.components.AppPreviewConstants.PREVIEW_LIGHT_THEME_BACKGROUND_COLOR
import com.ateca.ui.components.icon.ThemedIcon
import com.ateca.ui.components.icon.mirroringBackIcon
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing

/**
 * Created by dronpascal on 20.05.2022.
 */
private val IconSize = 48.dp
private val SearchBarHeight = 56.dp
private val SearchBarShape = RoundedCornerShape(50)

@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    searching: Boolean,
    modifier: Modifier = Modifier
) {
    val hintColor = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
    val focusManager = LocalFocusManager.current
    val onClearFocus = { focusManager.clearFocus(true) }

    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.025f),
        contentColor = MaterialTheme.colors.onSurface,
        shape = SearchBarShape,
        modifier = modifier
            .fillMaxWidth()
            .height(SearchBarHeight)
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            )
    ) {
        Box(Modifier.fillMaxSize()) {
            if (query.text.isEmpty()) {
                SearchHint()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                if (searchFocused) {
                    IconButton(onClick = {
                        onClearQuery()
                        onClearFocus()
                    }) {
                        ThemedIcon(
                            imageVector = mirroringBackIcon(),
                            tint = hintColor,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    cursorBrush = SolidColor(MaterialTheme.colors.onSurface.copy(alpha = 0.5f)),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        }
                )
                if (searching) {
                    CircularProgressIndicator(
                        color = hintColor,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.tiny)
                            .size(MaterialTheme.spacing.large)
                    )
                } else {
                    Spacer(Modifier.width(IconSize)) // balance arrow icon
                }
            }
        }
    }
}

@Composable
private fun SearchHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        val hintColor = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
        ThemedIcon(
            imageVector = Icons.Outlined.Search,
            tint = hintColor,
            contentDescription = stringResource(R.string.search)
        )
        Spacer(Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = stringResource(R.string.search_note),
            color = hintColor
        )
    }
}

@Preview(
    name = "SearchBarLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SearchBarDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SearchBarLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun SearchBarPreview() {
    AtecaTheme {
        SearchBar(
            query = TextFieldValue(""),
            onQueryChange = { },
            searchFocused = false,
            onSearchFocusChange = { },
            onClearQuery = { },
            searching = false
        )
    }
}

@Preview(
    name = "SearchBarFocusedLight",
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SearchBarFocusedDark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = PREVIEW_DARK_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Preview(
    name = "SearchBarFocusedLargeFont",
    fontScale = PREVIEW_FONT_SCALE,
    backgroundColor = PREVIEW_LIGHT_THEME_BACKGROUND_COLOR,
    showBackground = true
)
@Composable
private fun SearchBarFocusedPreview() {
    AtecaTheme {
        SearchBar(
            query = TextFieldValue("Ateca"),
            onQueryChange = { },
            searchFocused = true,
            onSearchFocusChange = { },
            onClearQuery = { },
            searching = true
        )
    }
}