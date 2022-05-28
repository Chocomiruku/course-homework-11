package com.chocomiruku.homework11.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.chocomiruku.homework11.domain.Employee

@Entity(
    tableName = "employee_table", foreignKeys = [ForeignKey(
        entity = DatabasePosition::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("positionId"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class DatabaseEmployee constructor(
    @PrimaryKey
    val id: Long,
    val fullName: String,
    val age: Int,
    val experience: Int,
    val positionId: Long
)

fun List<DatabaseEmployee>.asDomainModel(): List<Employee> {
    return map {
        Employee(
            id = it.id,
            fullName = it.fullName,
            age = it.age,
            experience = it.experience,
            positionId = it.positionId
        )
    }
}
