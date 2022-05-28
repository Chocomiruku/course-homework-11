package com.chocomiruku.homework11.employees

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EmployeesViewModelFactory(
    private val application: Application,
    private val context: Context
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeesViewModel::class.java)) {
            return EmployeesViewModel(application, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}