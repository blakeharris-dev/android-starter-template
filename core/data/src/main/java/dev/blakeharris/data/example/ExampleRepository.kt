package dev.blakeharris.data.example

import dev.blakeharris.model.Example
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {
    fun observeExamples(): Flow<List<Example>>
    suspend fun getExample(id: Long): Example?
    suspend fun saveExample(example: Example): Long
    suspend fun deleteExample(example: Example): Int
}
