package com.ateca.data.local.datastore

import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.ateca.domain.models.ApplicationSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<ApplicationSettings> {
    private const val TAG = "SettingsSerializer"
    override val defaultValue: ApplicationSettings = ApplicationSettings()

    override suspend fun readFrom(input: InputStream): ApplicationSettings =
        try {
            Json.decodeFromString(
                ApplicationSettings.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (ex: SerializationException) {
            Log.e(TAG, ex.message.toString())
            defaultValue
        }


    override suspend fun writeTo(t: ApplicationSettings, output: OutputStream) {
        try {
            output.write(
                Json.encodeToString(
                    serializer = ApplicationSettings.serializer(),
                    value = t
                )
                    .encodeToByteArray()
            )
        } catch (ex: Exception) {
            when (ex) {
                is IOException -> throw IOException("Unable to write file...", ex)
                is SerializationException ->
                    throw CorruptionException("Unable to write application settings...", ex)
                else -> throw Exception("Exception...", ex)
            }
        }
    }
}