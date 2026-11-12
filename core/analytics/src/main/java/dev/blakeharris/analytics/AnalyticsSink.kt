package dev.blakeharris.analytics

/**
 * Where logged analytics events end up: Logcat, Firebase, a custom backend, etc.
 *
 * Sinks only deliver events. Whether an event may be sent (consent) is determined before it
 * reaches a sink by an [AnalyticsLogger]. Nothing outside this module can use a sink directly,
 * therefore this check can never be skipped.
 *
 * To connect a custom backend, implement this and plug it in to `AnalyticsModule`. Sinks may wrap
 * other sinks to add behaviour, as [DeduplicatingAnalyticsSink] does.
 */
internal fun interface AnalyticsSink {

    /**
     * Delivers [event].
     */
    fun send(event: AnalyticsEvent)
}
