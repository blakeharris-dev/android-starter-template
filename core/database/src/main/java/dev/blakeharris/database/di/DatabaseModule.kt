package dev.blakeharris.database.di

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.common.coroutines.IoDispatcher
import dev.blakeharris.database.AppDatabase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * Provides the database and its DAOs. Only the DAOs are visible to other modules.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "app.db"

    /**
     * Uses the bundled SQLite driver, so every device runs the same SQLite version, and runs
     * queries on the IO dispatcher.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext ctx: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AppDatabase =
        Room.databaseBuilder<AppDatabase>(ctx, DATABASE_NAME)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(ioDispatcher)
            .build()
}
