package dev.blakeharris.logging

import android.util.Log

internal class LogcatLogger(
    private val minPriority: Int
) : Logger {

    override fun debug(tag: String, message: () -> String) =
        log(Log.DEBUG, tag, null, message)

    override fun info(tag: String, message: () -> String) =
        log(Log.INFO, tag, null, message)

    override fun warn(tag: String, throwable: Throwable?, message: () -> String) =
        log(Log.WARN, tag, throwable, message)

    override fun error(tag: String, throwable: Throwable?, message: () -> String) =
        log(Log.ERROR, tag, throwable, message)

    private fun log(priority: Int, tag: String, throwable: Throwable?, message: () -> String) {
        if (priority < minPriority) return

        val text = buildString {
            append(message())
            if (throwable != null) {
                append('\n')
                append(Log.getStackTraceString(throwable))
            }
        }
        Log.println(priority, tag, text)
    }

}
