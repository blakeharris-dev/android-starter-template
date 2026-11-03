package dev.blakeharris.common.coroutines

import javax.inject.Qualifier

/**
 * [kotlinx.coroutines.Dispatchers.IO]: disk and network work.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

/**
 * [kotlinx.coroutines.Dispatchers.Default]: CPU-bound work.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher
