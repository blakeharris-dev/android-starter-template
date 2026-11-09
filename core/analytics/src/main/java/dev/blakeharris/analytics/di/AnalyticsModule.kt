package dev.blakeharris.analytics.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.analytics.AnalyticsLogger
import dev.blakeharris.analytics.DebugAnalyticsSink
import dev.blakeharris.analytics.DeduplicatingAnalyticsSink
import dev.blakeharris.analytics.DefaultAnalyticsLogger
import dev.blakeharris.common.coroutines.ApplicationScope
import dev.blakeharris.datastore.AppPreferencesDataSource
import dev.blakeharris.logging.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {
    @Provides
    @Singleton
    fun provideAnalyticsLogger(
        logger: Logger,
        preferences: AppPreferencesDataSource,
        @ApplicationScope scope: CoroutineScope
    ): AnalyticsLogger =
        DefaultAnalyticsLogger(
            sink = DeduplicatingAnalyticsSink(DebugAnalyticsSink(logger)),
            consent = preferences.preferences
                .map { it.analyticsEnabled }
                .distinctUntilChanged(),
            scope = scope
        )
}
