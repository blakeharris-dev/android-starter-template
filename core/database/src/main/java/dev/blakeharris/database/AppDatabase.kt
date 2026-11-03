package dev.blakeharris.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import dev.blakeharris.database.example.ExampleDao
import dev.blakeharris.database.example.ExampleEntity

@Database(
    entities = [ExampleEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}
