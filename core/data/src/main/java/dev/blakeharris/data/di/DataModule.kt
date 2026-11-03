package dev.blakeharris.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.data.example.ExampleRepository
import dev.blakeharris.data.example.ExampleRepositoryImpl
import dev.blakeharris.database.example.ExampleDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Provides
    @Singleton
    fun provideExampleRepository(exampleDao: ExampleDao): ExampleRepository =
        ExampleRepositoryImpl(exampleDao)
}
