package com.chocomiruku.homework11.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chocomiruku.homework11.adapters.EmployeeAdapter
import com.chocomiruku.homework11.databinding.FragmentEmployeesBinding

class EmployeesFragment : Fragment() {
    private var _binding: FragmentEmployeesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmployeesViewModel by lazy {
        val activity = requireNotNull(this.activity) { }
        ViewModelProvider(
            this,
            EmployeesViewModelFactory(activity.application, requireContext())
        ).get(
            EmployeesViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeesBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = EmployeeAdapter()
        binding.employeesList.adapter = adapter

        savedInstanceState?.let {
            binding.employeesList.layoutManager?.onRestoreInstanceState(
                savedInstanceState.getParcelable(
                    LIST_STATE_KEY
                )
            )
        }

        return binding.root
    }

    // В моём случае положение RecyclerView и так сохранялось, но на всякий случай сделала и так
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            LIST_STATE_KEY,
            binding.employeesList.layoutManager?.onSaveInstanceState()
        )
    }

    companion object {
        const val LIST_STATE_KEY = "recycler_view_state"
    }
}