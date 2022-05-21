package com.ateca.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import com.ateca.ui.components.app.AtecaApp
import com.ateca.ui.theme.md2.AtecaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AtecaTheme {
                Box {
                    AtecaApp()
                }
            }
        }
    }
}