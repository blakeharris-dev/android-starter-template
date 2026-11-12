package dev.blakeharris.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.common.coroutines.ApplicationScope
import dev.blakeharris.common.coroutines.DefaultDispatcher
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Provides long-lived [CoroutineScope]s.
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopesModule {

    /**
     * One scope for the whole app. [SupervisorJob] stops a failed coroutine from cancelling the
     * others; work runs on the default dispatcher unless a caller specifies otherwise.
     */
    @Provides
    @Singleton
    @ApplicationScope
    fun provideApplicationScope(
        @DefaultDispatcher dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}
