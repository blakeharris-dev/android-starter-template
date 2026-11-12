package dev.blakeharris.datastore

/**
 * The app's saved settings. Read by [AppPreferencesDataSource].
 *
 * @property analyticsEnabled Whether the user has agreed to analytics. Defaults to `false` so
 * nothing is sent until the user opts in.
 */
data class AppPreferences(
    val analyticsEnabled: Boolean = false
)
