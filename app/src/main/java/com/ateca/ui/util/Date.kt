package com.ateca.ui.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by dronpascal on 20.05.2022.
 */
// TODO: Add different output formats
internal fun getFormatDate(timestamp: Long): String {
    val date = Date(timestamp)
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return dateFormat.format(date)
}