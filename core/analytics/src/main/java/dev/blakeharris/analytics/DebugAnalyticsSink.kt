package dev.blakeharris.analytics

import dev.blakeharris.logging.Logger

/**
 * Writes each event to [logger] at debug level using the tag "Analytics".
 *
 * Debug entries are only kept in debug builds, so events are visible while developing and
 * nothing is written in release. Nothing leaves the device.
 *
 */
class DebugAnalyticsSink(
    private val logger: Logger
) : AnalyticsSink {

    override fun send(event: AnalyticsEvent) {
        logger.debug(TAG, event.toString())
    }

    private companion object {
        const val TAG = "Analytics"
    }
}
