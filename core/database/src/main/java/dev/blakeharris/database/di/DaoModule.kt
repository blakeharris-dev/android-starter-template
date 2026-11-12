package dev.blakeharris.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.blakeharris.database.AppDatabase
import dev.blakeharris.database.example.ExampleDao
import javax.inject.Singleton

/**
 * Provides each DAO from [AppDatabase]. DAOs are the only part of this module other modules see;
 * the database itself stays `internal`.
 */
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideExampleDao(db: AppDatabase): ExampleDao = db.exampleDao()
}
