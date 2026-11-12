package dev.blakeharris.testing.fake

import dev.blakeharris.analytics.AnalyticsEvent
import dev.blakeharris.analytics.AnalyticsLogger

/**
 * An [AnalyticsLogger] that records every event, so tests can check what was logged.
 */
class FakeAnalyticsLogger : AnalyticsLogger {
    val events = mutableListOf<AnalyticsEvent>()

    override fun logEvent(event: AnalyticsEvent) {
        events += event
    }
}
