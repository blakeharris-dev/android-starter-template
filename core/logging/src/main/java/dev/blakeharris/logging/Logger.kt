package dev.blakeharris.logging

fun interface Logger {
    fun log(entry: LogEntry)

    fun verbose(tag: String, message: String) = log(LogEntry.Verbose(tag, message))
    fun debug(tag: String, message: String) = log(LogEntry.Debug(tag, message))
    fun info(tag: String, message: String) = log(LogEntry.Info(tag, message))
    fun warn(tag: String, message: String, throwable: Throwable? = null) =
        log(LogEntry.Warn(tag, message, throwable))
    fun error(tag: String, message: String, throwable: Throwable? = null) =
        log(LogEntry.Error(tag, message, throwable))
}
