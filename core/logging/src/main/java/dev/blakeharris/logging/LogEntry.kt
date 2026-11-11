package dev.blakeharris.logging

/**
 * A single event to log. Each level is its own type, so a test can compare logged entries
 * directly.
 *
 * Usually created through the [Logger] level functions rather than directly.
 *
 * @property level How severe this entry is; fixed by the entry's type.
 * @property tag Identifies where the entry came from, typically the class name.
 * @property message What happened. Never include personal data.
 */
sealed interface LogEntry {
    val level: LogLevel
    val tag: String
    val message: String

    /**
     * A [LogLevel.VERBOSE] entry.
     */
    data class Verbose(
        override val tag: String,
        override val message: String
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.VERBOSE
    }

    /**
     * A [LogLevel.DEBUG] entry.
     */
    data class Debug(
        override val tag: String,
        override val message: String
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.DEBUG
    }

    /**
     * A [LogLevel.INFO] entry.
     */
    data class Info(
        override val tag: String,
        override val message: String
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.INFO
    }

    /**
     * A [LogLevel.WARN] entry.
     *
     * @property throwable The exception that caused the warning, if there was one.
     */
    data class Warn(
        override val tag: String,
        override val message: String,
        val throwable: Throwable? = null
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.WARN
    }

    /**
     * A [LogLevel.ERROR] entry.
     *
     * @property throwable The exception that caused the error, if there was one.
     */
    data class Error(
        override val tag: String,
        override val message: String,
        val throwable: Throwable? = null
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.ERROR
    }
}
