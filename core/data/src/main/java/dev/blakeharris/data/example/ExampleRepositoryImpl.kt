package dev.blakeharris.data.example

import dev.blakeharris.database.example.ExampleDao
import dev.blakeharris.database.example.ExampleEntity
import dev.blakeharris.model.Example
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ExampleRepositoryImpl(
    private val exampleDao: ExampleDao,
) : ExampleRepository {

    override fun observeExamples(): Flow<List<Example>> =
        exampleDao.observeAll().map { entities ->
            entities.map(ExampleEntity::toDomain)
        }

    override suspend fun getExample(id: Long): Example? =
        exampleDao.getById(id)?.toDomain()

    override suspend fun saveExample(example: Example): Long =
        exampleDao.upsert(example.toEntity())

    override suspend fun deleteExample(example: Example) =
        exampleDao.delete(example.toEntity())
}
