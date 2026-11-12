package dev.blakeharris.analytics

/**
 * Records analytics events.
 *
 * Inject this wherever the app needs to log analytics.
 *
 * Events are only logged while the user has agreed to analytics; otherwise they're ignored.
 * Callers don't need to check consent themselves.
 *
 * For diagnostic logging that remains on the device, use `Logger` instead.
 */
fun interface AnalyticsLogger {

    /**
     * Log [event] if the user has consented to analytics; otherwise ignore it.
     */
    fun logEvent(event: AnalyticsEvent)
}
