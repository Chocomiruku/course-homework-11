package com.chocomiruku.homework11.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chocomiruku.homework11.domain.Position

@Entity(tableName = "position_table")
data class DatabasePosition constructor(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun List<DatabasePosition>.asDomainModel(): List<Position> {
    return map {
        Position(
            id = it.id,
            name = it.name
        )
    }
}
