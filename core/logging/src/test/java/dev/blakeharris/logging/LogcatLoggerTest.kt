package dev.blakeharris.logging

import android.util.Log
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
class LogcatLoggerTest {

    @Before
    fun setUp() {
        ShadowLog.clear()
    }

    @Test
    fun `log writes each level with matching priority`() {
        val logger = LogcatLogger(minLevel = LogLevel.VERBOSE)

        logger.verbose(TAG, "verbose")
        logger.debug(TAG, "debug")
        logger.info(TAG, "info")
        logger.warn(TAG, "warn")
        logger.error(TAG, "error")

        val expected = listOf(
            Log.VERBOSE to "verbose",
            Log.DEBUG to "debug",
            Log.INFO to "info",
            Log.WARN to "warn",
            Log.ERROR to "error",
        )
        val actual = ShadowLog.getLogsForTag(TAG).map { it.type to it.msg }
        assertEquals(expected, actual)
    }

    @Test
    fun log_dropsEntriesBelowMinLevel() {
        val logger = LogcatLogger(minLevel = LogLevel.WARN)

        logger.debug(TAG, "dropped")
        logger.info(TAG, "dropped")
        logger.warn(TAG, "kept")
        logger.error(TAG, "kept")

        assertEquals(
            listOf("kept", "kept"),
            ShadowLog.getLogsForTag(TAG).map { it.msg },
        )
    }

    @Test
    fun `log appends stack trace of throwable`() {
        val logger = LogcatLogger(minLevel = LogLevel.VERBOSE)

        logger.error(TAG, "Save failed", IllegalStateException("disk full"))

        val message = ShadowLog.getLogsForTag(TAG).single().msg
        assertTrue(message.startsWith("Save failed\n"))
        assertTrue(message.contains("java.lang.IllegalStateException: disk full"))
    }

    private companion object {
        const val TAG = "LogcatLoggerTest"
    }
}
