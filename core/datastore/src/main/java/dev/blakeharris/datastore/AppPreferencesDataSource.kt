package dev.blakeharris.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class AppPreferencesDataSource internal constructor(
    private val dataStore: DataStore<Preferences>
) {
    val preferences: Flow<AppPreferences> = dataStore.data
        .catch { error ->
            if (error is IOException) emit(emptyPreferences())
            else throw error
        }
        .map { prefs ->
            AppPreferences(
                exampleEnabled = prefs[Keys.EXAMPLE_ENABLED] ?: false
            )
        }

    suspend fun setExampleEnabled(enabled: Boolean) = dataStore.edit { prefs ->
        prefs[Keys.EXAMPLE_ENABLED] = enabled
    }

    private object Keys {
        val EXAMPLE_ENABLED = booleanPreferencesKey("example_enabled")
    }
}
