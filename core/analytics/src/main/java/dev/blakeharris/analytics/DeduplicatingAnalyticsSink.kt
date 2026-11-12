package dev.blakeharris.analytics

/**
 * Ignores [AnalyticsEvent.ScreenView] events that are identical, and passes all other events to
 * [delegate]
 *
 * Rotation, recomposition and returning from the background can all report the same screen
 * again. This keeps one view per visit, without every screen having to guard against it.
 *
 * Only screen views are compared: tapping a button twice is two real actions. Other events don't
 * reset the last screen view either, so `home`, a tap, then `home` again records one view.
 */
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
