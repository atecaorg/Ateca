package com.ateca.ui.screens.note_list.view.components.search

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import com.ateca.domain.models.Note

/**
 * Created by dronpascal on 21.05.2022.
 */
@Composable
fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    searchResults: List<Note> = emptyList()
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
            searchResults = searchResults
        )
    }
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    searchResults: List<Note>
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
    var searchResults by mutableStateOf(searchResults)
    val searchDisplay: SearchDisplay
        get() = when {
            focused && query.text.isEmpty() -> SearchDisplay.Idle
            searchResults.isNotEmpty() -> SearchDisplay.Results
            else -> SearchDisplay.NoResults
        }
}
