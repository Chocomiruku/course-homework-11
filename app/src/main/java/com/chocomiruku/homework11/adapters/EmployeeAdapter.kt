package com.chocomiruku.homework11.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocomiruku.homework11.databinding.ListItemEmployeeBinding
import com.chocomiruku.homework11.domain.Employee
import com.chocomiruku.homework11.util.formatYears

class EmployeeAdapter :
    ListAdapter<Employee, EmployeeAdapter.ViewHolder>(FactDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = getItem(position)
        holder.bind(employee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: Employee) {
            binding.fullNameText.text = employee.fullName
            binding.ageText.text = employee.age.formatYears()
            binding.experienceText.text = employee.experience.formatYears()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemEmployeeBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class FactDiffCallback :
        DiffUtil.ItemCallback<Employee>() {

        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }
}