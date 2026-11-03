package dev.blakeharris.database.example

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import androidx.room3.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ExampleDao {

    // Create
    @Insert
    suspend fun add(example: ExampleEntity): Long

    // Retrieve
    @Query("SELECT * FROM examples ORDER BY name ASC")
    suspend fun all(): List<ExampleEntity>

    @Query("SELECT * FROM examples ORDER BY created_at DESC")
    fun observeAll(): Flow<List<ExampleEntity>>

    @Query("SELECT ONE FROM examples WHERE id = :id")
    suspend fun getById(id: Long): ExampleEntity?

    // Update
    @Upsert
    suspend fun upsert(example: ExampleEntity): Long

    // Delete
    @Delete
    suspend fun delete(example: ExampleEntity): Int

    @Delete
    suspend fun deleteById(id: Long): Int
}
