package dev.blakeharris.common.coroutines

import javax.inject.Qualifier

/**
 * A [kotlinx.coroutines.CoroutineScope] that lives as long as the app process.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationScope
