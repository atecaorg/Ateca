package com.ateca.ui.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ateca.BuildConfig

/**
 * Created by dronpascal on 19.05.2022.
 */
fun Context.openGooglePlay() {
    try {
        val uriString = "market://details?id=${BuildConfig.APPLICATION_ID}"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uriString)))
    } catch (e: ActivityNotFoundException) {
        val webUriString =
            "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webUriString)))
    }
}