package dev.blakeharris.analytics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

internal class DefaultAnalyticsLogger(
    private val sink: AnalyticsSink,
    consent: Flow<Boolean>,
    scope: CoroutineScope
) : AnalyticsLogger {

    private val isEnabled = consent.stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    override fun logEvent(event: AnalyticsEvent) {
        if (isEnabled.value) sink.send(event)
    }
}
