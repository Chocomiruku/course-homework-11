package com.chocomiruku.homework11.positions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chocomiruku.homework11.adapters.PositionAdapter
import com.chocomiruku.homework11.databinding.FragmentPositionsBinding


class PositionsFragment : Fragment() {
    private var _binding: FragmentPositionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PositionsViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            PositionsViewModelFactory(activity.application, requireContext())
        ).get(
            PositionsViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPositionsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.positionsList.adapter = PositionAdapter()

        binding.goToEmployeesBtn.setOnClickListener {
            findNavController().navigate(PositionsFragmentDirections.actionPositionsFragmentToEmployeesFragment())
        }

        return binding.root
    }
}