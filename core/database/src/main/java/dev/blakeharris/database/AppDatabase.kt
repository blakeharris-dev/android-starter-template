package dev.blakeharris.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import dev.blakeharris.database.example.ExampleDao
import dev.blakeharris.database.example.ExampleEntity

/**
 * The app's database. Other modules never see it: they inject DAOs instead.
 *
 * When an entity changes, bump `version` and add a migration. The exported schema in `core/
 * database/schemas/` is committed so Room can check migrations against it.
 */
@Database(
    entities = [ExampleEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}
