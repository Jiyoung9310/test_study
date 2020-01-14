package com.example.mytestapplication.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<T>(private val binding: ViewDataBinding, private val listener: AdapterItemClickListener? = null) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        listener?.let { binding.setVariable(BR.listener, it) }
        binding.executePendingBindings()
    }
}