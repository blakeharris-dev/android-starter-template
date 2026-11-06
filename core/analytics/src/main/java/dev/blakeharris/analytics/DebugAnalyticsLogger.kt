package dev.blakeharris.analytics

import dev.blakeharris.logging.Logger

internal class DebugAnalyticsLogger(
    private val logger: Logger
) : AnalyticsLogger {

    override fun logEvent(event: AnalyticsEvent) {
        logger.debug(TAG, event.toString())
    }

    private companion object {
        const val TAG = "analytics"
    }
}
