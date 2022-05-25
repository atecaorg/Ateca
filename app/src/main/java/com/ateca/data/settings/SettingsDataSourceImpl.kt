package com.ateca.data.settings

import android.util.Log
import androidx.datastore.core.DataStore
import com.ateca.UserSettings
import com.ateca.domain.datasource.ISettingsDataSource
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<UserSettings>
) : ISettingsDataSource {

    override suspend fun getSetting(): Flow<UserSettings> =
        dataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    Log.e(TAG, ex.message.toString())
                    emit(UserSettings.getDefaultInstance())
                } else {
                    throw ex
                }
            }
            .map { userSettings ->
                userSettings
            }

    override suspend fun setSetting(settings: UserSettings) {
        dataStore.updateData {
            settings
        }
    }

    companion object {
        const val TAG = "UserSettingsRepo"
    }
}