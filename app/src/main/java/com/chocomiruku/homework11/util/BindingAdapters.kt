package com.chocomiruku.homework11.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocomiruku.homework11.adapters.EmployeeAdapter
import com.chocomiruku.homework11.adapters.PositionAdapter
import com.chocomiruku.homework11.domain.Employee
import com.chocomiruku.homework11.domain.Position
import com.google.android.material.progressindicator.CircularProgressIndicator

@BindingAdapter("listData")
fun bindPositionsRecyclerView(recyclerView: RecyclerView, data: List<Position>?) {
    val adapter = recyclerView.adapter as PositionAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindEmployeesRecyclerView(recyclerView: RecyclerView, data: List<Employee>?) {
    val adapter = recyclerView.adapter as EmployeeAdapter
    adapter.submitList(data)
}

@BindingAdapter("loadingStatus")
fun bindStatusRecyclerView(recyclerView: RecyclerView, status: LoadingStatus?) {
    when (status) {
        LoadingStatus.DONE -> {
            recyclerView.visibility = View.VISIBLE
        }
        else -> {
            recyclerView.visibility = View.GONE
        }
    }
}

@BindingAdapter("loadingStatus")
fun bindStatusProgressIndicator(
    statusProgressIndicator: CircularProgressIndicator,
    status: LoadingStatus?
) {
    when (status) {
        LoadingStatus.LOADING -> {
            statusProgressIndicator.show()
        }
        else -> {
            statusProgressIndicator.hide()
        }
    }
}