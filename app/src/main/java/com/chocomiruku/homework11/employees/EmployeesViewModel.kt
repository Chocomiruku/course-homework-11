package com.chocomiruku.homework11.employees

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocomiruku.homework11.database.getDatabase
import com.chocomiruku.homework11.repository.EmployeesRepository
import com.chocomiruku.homework11.util.LoadingStatus
import kotlinx.coroutines.launch

class EmployeesViewModel(application: Application, context: Context) : ViewModel() {
    private val database = getDatabase(application)
    private val employeesRepository = EmployeesRepository(context, database)

    private val _status = MutableLiveData<LoadingStatus>()
    val status: LiveData<LoadingStatus>
        get() = _status

    init {
        viewModelScope.launch {
            _status.value = LoadingStatus.LOADING
            try {
                employeesRepository.getEmployees()
                _status.value = LoadingStatus.DONE
            } catch (e: Exception) {
                _status.value = LoadingStatus.ERROR
            }
        }
    }

    val employees = employeesRepository.employees
}