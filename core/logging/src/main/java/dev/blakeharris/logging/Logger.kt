package dev.blakeharris.logging

/**
 * App-wide diagnostic logging.
 *
 * Inject this rather than calling `android.util.Log` so the implementation can be changed in one
 * place and replaced in tests (e.g. to add crash reporting).
 *
 * Implementations only override [log]. The level functions are conveniences that build the
 * matching [LogEntry], so every implementation handles every level the same way.
 *
 * As this is a `fun interface`, a test fake is a single lambda:
 * ```kotlin
 * val entries = mutableListOf<LogEntry>()
 * val logger = Logger { entries += it }
 * ```
 *
 * For analytics events, use `AnalyticsLogger` instead.
 */
fun interface Logger {
    /**
     * Records [entry]. Implementations decide where it goes and which levels to keep.
     */
    fun log(entry: LogEntry)

    /**
     * Logs a [LogEntry.Verbose]
     */
    fun verbose(tag: String, message: String) = log(LogEntry.Verbose(tag, message))

    /**
     * Logs a [LogEntry.Debug]
     */
    fun debug(tag: String, message: String) = log(LogEntry.Debug(tag, message))

    /**
     * Logs a [LogEntry.Info]
     */
    fun info(tag: String, message: String) = log(LogEntry.Info(tag, message))

    /**
     * Logs a [LogEntry.Warn]
     */
    fun warn(tag: String, message: String, throwable: Throwable? = null) =
        log(LogEntry.Warn(tag, message, throwable))

    /**
     * Logs a [LogEntry.Error]
     */
    fun error(tag: String, message: String, throwable: Throwable? = null) =
        log(LogEntry.Error(tag, message, throwable))
}
