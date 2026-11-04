package dev.blakeharris.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.common.coroutines.ApplicationScope
import dev.blakeharris.common.coroutines.IoDispatcher
import dev.blakeharris.datastore.AppPreferencesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    private const val PREFS_FILE_NAME = "app_preferences"

    @Provides
    @Singleton
    fun provideAppPreferences(
        @ApplicationContext ctx: Context,
        @ApplicationScope scope: CoroutineScope,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            scope = scope,
            produceFile = { ctx.preferencesDataStoreFile(PREFS_FILE_NAME)}
        )

    @Provides
    @Singleton
    fun provideAppPreferencesDataSource(
        dataStore: DataStore<Preferences>
    ): AppPreferencesDataSource = AppPreferencesDataSource(dataStore)
}
