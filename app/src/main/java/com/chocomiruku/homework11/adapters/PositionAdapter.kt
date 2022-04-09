package com.chocomiruku.homework11.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chocomiruku.homework11.databinding.ListItemPositionBinding
import com.chocomiruku.homework11.domain.Position

class PositionAdapter :
    ListAdapter<Position, PositionAdapter.ViewHolder>(FactDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemPositionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Position) {
            binding.nameText.text = position.name
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemPositionBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class FactDiffCallback :
        DiffUtil.ItemCallback<Position>() {

        override fun areItemsTheSame(oldItem: Position, newItem: Position): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Position, newItem: Position): Boolean {
            return oldItem == newItem
        }
    }
}