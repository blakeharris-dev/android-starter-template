package dev.blakeharris.analytics

import dev.blakeharris.logging.Logger

class DebugAnalyticsSink(
    private val logger: Logger
) : AnalyticsSink {
    override fun send(event: AnalyticsEvent) {
        logger.debug(TAG, event.toString())
    }

    private companion object {
        const val TAG = "analytics"
    }
}
