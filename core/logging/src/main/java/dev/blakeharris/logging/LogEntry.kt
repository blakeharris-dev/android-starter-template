package dev.blakeharris.logging

enum class LogLevel { VERBOSE, DEBUG, INFO, WARN, ERROR }

sealed interface LogEntry {
    val level: LogLevel
    val tag: String
    val message: String

    data class Verbose(
        override val tag: String,
        override val message: String
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.VERBOSE
    }

    data class Debug(
        override val tag: String,
        override val message: String
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.DEBUG
    }

    data class Info(
        override val tag: String,
        override val message: String
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.INFO
    }

    data class Warn(
        override val tag: String,
        override val message: String,
        val throwable: Throwable? = null
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.WARN
    }

    data class Error(
        override val tag: String,
        override val message: String,
        val throwable: Throwable? = null
    ) : LogEntry {
        override val level: LogLevel
            get() = LogLevel.ERROR
    }
}
