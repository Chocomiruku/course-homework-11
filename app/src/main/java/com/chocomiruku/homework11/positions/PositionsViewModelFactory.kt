package com.chocomiruku.homework11.positions

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PositionsViewModelFactory(
    private val application: Application,
    private val context: Context
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PositionsViewModel::class.java)) {
            return PositionsViewModel(application, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}