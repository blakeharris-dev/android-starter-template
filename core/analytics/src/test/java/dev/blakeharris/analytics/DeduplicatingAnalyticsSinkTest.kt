package dev.blakeharris.analytics

import org.junit.Assert.assertEquals
import org.junit.Test

class DeduplicatingAnalyticsSinkTest {

    private val sent = mutableListOf<AnalyticsEvent>()
    private val sink = DeduplicatingAnalyticsSink { sent += it }

    private val home = AnalyticsEvent.ScreenView(
        screenName = "home",
        screenClass = "HomeScreen"
    )
    private val settings = AnalyticsEvent.ScreenView(
        screenName = "settings",
        screenClass = "SettingsScreen"
    )
    private val tap = AnalyticsEvent.ButtonClick(
        screenName = "home",
        buttonText = "add_example",
        buttonType = AnalyticsEvent.ButtonClick.ButtonType.FAB,
    )

    @Test
    fun `Repeated screen view events are ignored`() {
        sink.send(home)
        sink.send(home)
        assertEquals(listOf(home), sent)
    }

    @Test
    fun `Scree view after another screen view is sent`() {
        sink.send(home)
        sink.send(settings)
        sink.send(home)

        assertEquals(listOf(home, settings, home), sent)
    }

    @Test
    fun `Screen views with same screen name but different screen classes are sent`() {
        val rebuiltHome = home.copy(screenClass = "HomeScreenV2")

        sink.send(home)
        sink.send(rebuiltHome)

        assertEquals(listOf(home, rebuiltHome), sent)
    }

    @Test
    fun `Repeated button taps are sent and never ignored`() {
        sink.send(tap)
        sink.send(tap)

        assertEquals(listOf(tap, tap), sent)
    }

    @Test
    fun `Button taps between same screen view event does not reset last screen view event`() {
        sink.send(home)
        sink.send(tap)
        sink.send(home)

        assertEquals(listOf(home, tap), sent)
    }
}
