package dev.blakeharris.logging

import android.util.Log

/**
 * Writes entries to Logcat.
 *
 * Entries below [minLevel] are dropped. A [Throwable] attached to a warning or error is appended
 * to the message as a stack trace.
 *
 * @param minLevel The least severe level that's written.
 */
internal class LogcatLogger(
    private val minLevel: LogLevel
) : Logger {

    override fun log(entry: LogEntry) {
        if (entry.level < minLevel) return

        val throwable = when (entry) {
            is LogEntry.Warn -> entry.throwable
            is LogEntry.Error -> entry.throwable
            else -> null
        }

        val text = if (throwable == null) {
            entry.message
        } else {
            "${entry.message}\n${Log.getStackTraceString(throwable)}"
        }

        Log.println(entry.level.priority, entry.tag, text)
    }

    /**
     * The `android.util.Log` priority for each level.
     */
    private val LogLevel.priority: Int
        get() = when (this) {
            LogLevel.VERBOSE -> Log.VERBOSE
            LogLevel.DEBUG -> Log.DEBUG
            LogLevel.INFO -> Log.INFO
            LogLevel.WARN -> Log.WARN
            LogLevel.ERROR -> Log.ERROR
        }
}
