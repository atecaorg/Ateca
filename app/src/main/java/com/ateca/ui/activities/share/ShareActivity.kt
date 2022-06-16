package com.ateca.ui.activities.share

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ateca.R
import com.ateca.domain.datasource.ISettingsDataSource
import com.ateca.domain.entity.Theme
import com.ateca.domain.interactors.NoteInteractors
import com.ateca.domain.models.ApplicationSettings
import com.ateca.domain.models.Note
import com.ateca.ui.theme.md2.AtecaTheme
import com.ateca.ui.theme.spacing
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class ShareActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: ISettingsDataSource

    @Inject
    lateinit var noteInteractors: NoteInteractors

    private val settings = MutableStateFlow(ApplicationSettings())
    private val scope = CoroutineScope(Dispatchers.IO)

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentTextData = getTextIntentData()

        if (intentTextData.isNotEmpty()) {
            scope.launch {
                dataStore.getSetting().collect {
                    settings.value = it
                }
            }

            val note = Note(
                title = intentTextData[SUBJECT_KEY] ?: EMPTY_STRING,
                text = intentTextData[TEXT_KEY] ?: EMPTY_STRING
            )

            setContent {
                val composeTheme = settings.collectAsState().value.theme
                val isDark = mutableStateOf(
                    when (composeTheme) {
                        Theme.DARK -> true
                        Theme.LIGHT -> false
                        else -> isSystemInDarkTheme()
                    }
                )

                AtecaTheme(isDarkTheme = isDark.value) {
                    ShareDialog(
                        title = note.title,
                        text = note.text
                    ) {
                        scope.launch {
                            noteInteractors.saveNote.execute(note).collect {}
                        }
                        Toast.makeText(
                            this,
                            getString(R.string.note_saved),
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            }
        }
    }

    @Composable
    private fun ShareDialog(
        title: String,
        text: String,
        onSaveShare: () -> Unit
    ) {
        AlertDialog(
            shape = RoundedCornerShape(16.dp),
            onDismissRequest = { finish() },
            confirmButton = {
                TextButton(onClick = onSaveShare) {
                    Text(text = stringResource(R.string.save))
                }
            },
            dismissButton = {},
            title = {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6
                    )
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)
                    )
                }
            },
            text = { Text(text = text) }
        )
    }

    private fun getTextIntentData(): Map<String, String> {
        val intentTextData = mutableMapOf<String, String>()
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            intentTextData[SUBJECT_KEY] =
                intent.getStringExtra(Intent.EXTRA_SUBJECT) ?: EMPTY_STRING

            val text = intent.getStringExtra(Intent.EXTRA_TEXT) ?: EMPTY_STRING
            intentTextData[TEXT_KEY] = parseTextForLinks(text = text)
        }
        return intentTextData
    }

    private fun parseTextForLinks(text: String): String {
        val pattern = Patterns.WEB_URL
        val regex = Regex("\\s+")
        val words: MutableList<String> = text.split(regex = regex) as MutableList<String>
        val newWords: MutableList<String> = mutableListOf()

        try {
            for (word in words) {
                if (pattern.matcher(word).find()) {
                    if (word.contains("http://") || word.contains("https://")) {
                        newWords.add("[$word]($word)")
                    } else {
                        newWords.add("[$word](http://$word)")
                    }
                } else {
                    newWords.add(word)
                }
            }
        } catch (e: Exception) {
            Log.d("PARSE_ERROR", e.message.toString())
        }

        return newWords.joinToString(
            separator = " "
        )
    }


    companion object {
        private const val SUBJECT_KEY = "SUBJECT"
        private const val TEXT_KEY = "TEXT"
        private const val EMPTY_STRING = ""
    }
}