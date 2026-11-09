package dev.blakeharris.analytics.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.analytics.AnalyticsLogger
import dev.blakeharris.analytics.DebugAnalyticsSink
import dev.blakeharris.analytics.DefaultAnalyticsLogger
import dev.blakeharris.logging.Logger
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {
    @Provides
    @Singleton
    fun provideAnalyticsLogger(logger: Logger): AnalyticsLogger =
        DefaultAnalyticsLogger(sink = DebugAnalyticsSink(logger))
}
