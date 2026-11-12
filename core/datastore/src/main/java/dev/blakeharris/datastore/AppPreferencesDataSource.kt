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

/**
 * Reads and saves [AppPreferences] on the device.
 *
 * Only Hilt creates this (the constructor is `internal`), so the whole app shares one instance and
 * one DataStore file.
 *
 */
class AppPreferencesDataSource internal constructor(
    private val dataStore: DataStore<Preferences>
) {

    /**
     * The current settings. Emits again whenever any setting changes.
     *
     * If the file can't be read, emits the defaults rather than failing.
     */
    val preferences: Flow<AppPreferences> = dataStore.data
        .catch { error ->
            if (error is IOException) emit(emptyPreferences())
            else throw error
        }
        .map { prefs ->
            AppPreferences(
                analyticsEnabled = prefs[Keys.ANALYTICS_ENABLED] ?: false
            )
        }

    /**
     * Saves whether the user has agreed to analytics.
     */
    suspend fun setAnalyticsEnabled(enabled: Boolean) {
        dataStore.edit { prefs -> prefs[Keys.ANALYTICS_ENABLED] = enabled }
    }

    /**
     * The keys settings are stored under. These strings are written to users' devices: Renaming
     * one silently resets that setting for every user.
     */
    private object Keys {
        val ANALYTICS_ENABLED = booleanPreferencesKey("analytics_enabled")
    }
}
