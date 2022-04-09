package com.chocomiruku.homework11.domain

import com.chocomiruku.homework11.database.entities.DatabasePosition

data class Position(
    val id: Long,
    val name: String
)

fun List<Position>.asDatabaseModel(): Array<DatabasePosition> {
    return map {
        DatabasePosition(
            id = it.id,
            name = it.name
        )
    }.toTypedArray()
}
