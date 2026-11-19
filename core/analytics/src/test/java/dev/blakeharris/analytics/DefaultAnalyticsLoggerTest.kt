package dev.blakeharris.analytics

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultAnalyticsLoggerTest {
    private val sent = mutableListOf<AnalyticsEvent>()
    private val event = AnalyticsEvent.ScreenView(screenName = "home", screenClass = "HomeScreen")

    private fun TestScope.createLogger(consent: Flow<Boolean>) = DefaultAnalyticsLogger(
        sink = { sent += it },
        consent = consent,
        scope = backgroundScope
    )

    @Test
    fun `events are dropped before consent has loaded`() = runTest(UnconfinedTestDispatcher()) {
        val logger = createLogger(consent = MutableSharedFlow())

        logger.logEvent(event)

        assertEquals(emptyList<AnalyticsEvent>(), sent)
    }

    @Test
    fun `events are dropped without consent`() = runTest(UnconfinedTestDispatcher()) {
        val logger = createLogger(consent = MutableStateFlow(false))

        logger.logEvent(event)

        assertEquals(emptyList<AnalyticsEvent>(), sent)
    }

    @Test
    fun `events are sent with consent`() = runTest(UnconfinedTestDispatcher()) {
        val logger = createLogger(consent = MutableStateFlow(true))

        logger.logEvent(event)

        assertEquals(listOf(event), sent)
    }

    @Test
    fun `events are sent once consent is given`() = runTest(UnconfinedTestDispatcher()) {
        val consent = MutableStateFlow(false)
        val logger = createLogger(consent)

        logger.logEvent(event)
        consent.value = true
        logger.logEvent(event)

        assertEquals(listOf(event), sent)
    }

    @Test
    fun `events are dropped once consent is withdrawn`() = runTest(UnconfinedTestDispatcher()) {
        val consent = MutableStateFlow(true)
        val logger = createLogger(consent)

        logger.logEvent(event)
        consent.value = false
        logger.logEvent(event)

        assertEquals(listOf(event), sent)
    }
}
