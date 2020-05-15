package tech.michalik.mvx

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindableViewHolder<T>(val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    open fun bind(viewModel: T) {
        binding.executePendingBindings()
    }
}