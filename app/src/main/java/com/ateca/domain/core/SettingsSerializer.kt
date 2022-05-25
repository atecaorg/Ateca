package com.ateca.domain.core

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.ateca.UserSettings
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<UserSettings> {
    override val defaultValue: UserSettings = UserSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserSettings =
        try {
            UserSettings.parseFrom(input)
        } catch (ex: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto...", ex)
        }

    override suspend fun writeTo(t: UserSettings, output: OutputStream) = t.writeTo(output)
}