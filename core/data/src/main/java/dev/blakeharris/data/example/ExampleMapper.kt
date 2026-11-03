package dev.blakeharris.data.example

import dev.blakeharris.database.example.ExampleEntity
import dev.blakeharris.model.Example

internal fun ExampleEntity.toDomain(): Example = Example(
    id = id,
    name = name,
    createdAt = createdAt,
)

internal fun Example.toEntity(): ExampleEntity = ExampleEntity(
    id = id,
    name = name,
    createdAt = createdAt,
)
