package dev.blakeharris.logging.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.logging.LogLevel
import dev.blakeharris.logging.LogcatLogger
import dev.blakeharris.logging.Logger
import dev.blakeharris.template.core.logging.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LoggingModule {

    /**
     * Logs everything in debug-flavoured builds, and only warnings and errors in release.
     *
     * To log to more than one place (e.g. crash reporting), provide a [Logger] that forwards each
     * entry to several others.
     */
    @Provides
    @Singleton
    fun provideLogger(): Logger = LogcatLogger(
        minLevel = if (BuildConfig.DEBUG) LogLevel.VERBOSE else LogLevel.WARN,
    )
}
