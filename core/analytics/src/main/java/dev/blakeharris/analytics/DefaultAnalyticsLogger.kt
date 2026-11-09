package dev.blakeharris.analytics

internal class DefaultAnalyticsLogger(
    private val sink: AnalyticsSink
) : AnalyticsLogger {
    override fun logEvent(event: AnalyticsEvent) = sink.send(event)
}
