package dev.blakeharris.analytics

import dev.blakeharris.logging.LogEntry
import dev.blakeharris.logging.Logger
import org.junit.Assert.assertEquals
import org.junit.Test

class DebugAnalyticsSinkTest {

    private val entries = mutableListOf<LogEntry>()
    private val logger = Logger { entries += it }
    private val sink = DebugAnalyticsSink(logger)

    @Test
    fun `send screen view logs all fields at debug level`() {
        sink.send(
            AnalyticsEvent.ScreenView(screenName = "home", screenClass = "HomeScreen")
        )
        val expected = listOf(
            LogEntry.Debug(
                tag = "Analytics",
                message = "ScreenView(screenName=home, screenClass=HomeScreen)"
            )
        )
        assertEquals(expected, entries)
    }

    @Test
    fun `send button click logs all fields at debug level`() {
        sink.send(
            AnalyticsEvent.ButtonClick(
                screenName = "home",
                buttonText = "add_example",
                buttonType = AnalyticsEvent.ButtonClick.ButtonType.FAB
            )
        )
        val expected = listOf(
            LogEntry.Debug(
                tag = "Analytics",
                message = "ButtonClick(screenName=home, buttonText=add_example, buttonType=FAB)"
            )
        )
        assertEquals(expected, entries)
    }
}
