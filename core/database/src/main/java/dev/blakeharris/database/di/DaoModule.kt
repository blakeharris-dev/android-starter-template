package dev.blakeharris.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.database.AppDatabase
import dev.blakeharris.database.example.ExampleDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideExampleDao(db: AppDatabase): ExampleDao = db.exampleDao()
}
