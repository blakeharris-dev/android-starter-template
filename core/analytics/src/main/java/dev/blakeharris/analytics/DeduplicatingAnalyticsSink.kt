package dev.blakeharris.analytics

internal class DeduplicatingAnalyticsSink(
    private val delegate: AnalyticsSink
) : AnalyticsSink {

    private var lastScreenView: AnalyticsEvent.ScreenView? = null

    @Synchronized
    override fun send(event: AnalyticsEvent) {
        if (event is AnalyticsEvent.ScreenView) {
            if (event == lastScreenView) return
            lastScreenView = event
        }
        delegate.send(event)
    }
}