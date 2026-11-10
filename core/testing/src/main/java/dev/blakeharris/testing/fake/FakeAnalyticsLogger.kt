package dev.blakeharris.testing.fake

import dev.blakeharris.analytics.AnalyticsEvent
import dev.blakeharris.analytics.AnalyticsLogger

class FakeAnalyticsLogger : AnalyticsLogger {
    val events = mutableListOf<AnalyticsEvent>()

    override fun logEvent(event: AnalyticsEvent) {
        events += event
    }
}
