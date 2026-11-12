package dev.blakeharris.analytics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 * Passes events to [sink] only while the user has agreed to analytics.
 *
 * Consent is read from [consent] rather than passed in by callers, so it's checked in exactly
 * one place. The user's choice is changed elsewhere (e.g. a settings screen saving it); this
 * class only reacts to it.
 *
 * @param sink Where allowed events are sent
 * @param consent The user's saved analytics choice. A change applies to the next event logged
 * @param scope A scope that lives as long as the app, used to keep watching [consent]

 */
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
