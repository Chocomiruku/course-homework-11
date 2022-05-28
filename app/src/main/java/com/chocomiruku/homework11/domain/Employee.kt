package com.chocomiruku.homework11.domain

import com.chocomiruku.homework11.database.entities.DatabaseEmployee

data class Employee(
    val id: Long,
    val fullName: String,
    val age: Int,
    val experience: Int,
    val positionId: Long
)

fun List<Employee>.asDatabaseModel(): Array<DatabaseEmployee> {
    return map {
        DatabaseEmployee(
            id = it.id,
            fullName = it.fullName,
            age = it.age,
            experience = it.experience,
            positionId = it.positionId
        )
    }.toTypedArray()
}
