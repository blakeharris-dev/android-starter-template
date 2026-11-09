package dev.blakeharris.analytics

internal fun interface AnalyticsSink {
    fun send(event: AnalyticsEvent)
}
