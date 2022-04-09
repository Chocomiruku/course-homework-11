package com.chocomiruku.homework11.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.chocomiruku.homework11.database.CompanyDatabase
import com.chocomiruku.homework11.database.entities.asDomainModel
import com.chocomiruku.homework11.domain.Employee
import com.chocomiruku.homework11.domain.asDatabaseModel
import com.chocomiruku.homework11.util.getEmployeesFromJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeesRepository(private val context: Context, private val database: CompanyDatabase) {

    val employees: LiveData<List<Employee>> =
        Transformations.map(database.employeeDao.getEmployees()) {
            it.asDomainModel()
        }

    suspend fun getEmployees() {
        if (!wasRefreshed) {
            withContext(Dispatchers.IO) {
                val employees = getEmployeesFromJson(context)
                database.employeeDao.insertAll(*employees.asDatabaseModel())
            }
            wasRefreshed = true
        }
    }

    companion object {
        var wasRefreshed: Boolean = false
    }
}