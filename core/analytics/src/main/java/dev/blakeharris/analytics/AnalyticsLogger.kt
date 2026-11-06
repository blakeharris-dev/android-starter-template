package dev.blakeharris.analytics

fun interface AnalyticsLogger {
    fun logEvent(event: AnalyticsEvent)
}
