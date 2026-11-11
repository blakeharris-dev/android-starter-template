package dev.blakeharris.logging

/**
 * [LogEntry] severity.
 *
 * Declared from least to most severe, so levels can be compared: `level >= LogLevel.WARN`
 */
enum class LogLevel {
    /**
     * Very detail tracing, normally only useful while debugging a specific problem.
     */
    VERBOSE,

    /**
     * Diagnostic information for devs.
     */
    DEBUG,

    /**
     * A notable, but expected event.
     */
    INFO,

    /**
     * Something unexpected that the app recovered from.
     */
    WARN,

    /**
     * A failure that affected what the user was doing.
     */
    ERROR
}
