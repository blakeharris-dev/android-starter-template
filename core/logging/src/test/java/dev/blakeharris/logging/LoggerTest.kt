package dev.blakeharris.logging

import org.junit.Assert.assertEquals
import org.junit.Test

class LoggerTest {

    private val entries = mutableListOf<LogEntry>()
    private val logger = Logger { entries += it }

    @Test
    fun `level functions build matching entries`() {
        logger.verbose("Tag", "verbose")
        logger.debug("Tag", "debug")
        logger.info("Tag", "info")
        logger.warn("Tag", "warn")
        logger.error("Tag", "error")

        val expected = listOf(
            LogEntry.Verbose("Tag", "verbose"),
            LogEntry.Debug("Tag", "debug"),
            LogEntry.Info("Tag", "info"),
            LogEntry.Warn("Tag", "warn", null),
            LogEntry.Error("Tag", "error", null),
        )
        assertEquals(expected, entries)
    }

    @Test
    fun `warn and error functions keep their throwables`() {
        val cause = IllegalStateException("boom")

        logger.warn("Tag", "warn", cause)
        logger.error("Tag", "error", cause)

        val expected = listOf(
            LogEntry.Warn("Tag", "warn", cause),
            LogEntry.Error("Tag", "error", cause)
        )
        assertEquals(expected, entries)
    }
}
